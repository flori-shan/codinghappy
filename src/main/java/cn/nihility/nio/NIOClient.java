package cn.nihility.nio;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yzx on 2019/5/20.
 */
public class NIOClient {

    private final static int BUFFER_SIZE = 1024;
    private ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

    private Selector selector;
    private final static int PORT = 60000;

    public void init(String address) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress(address, PORT));
    }

    public void connection() throws IOException {
        while (true) {
            int select = selector.select();
            if (0 == select) { continue; }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey key = selectionKeyIterator.next();
                selectionKeyIterator.remove();
                handleKey(key);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleKey(SelectionKey key) throws IOException {
        if (key.isConnectable()) { // 是否可连接
            SocketChannel channel = (SocketChannel) key.channel();

            if (channel.isConnectionPending()) {
                channel.finishConnect();
                LogbackUtil.logger(NIOClient.class, LoggerLevelEnum.DEBUG, "Connection. NIO Client Connection success.");

                String sentMessage = "Connection. Hello Socket Server. Time tag : " + System.currentTimeMillis();
                buffer.clear();
                buffer.put(sentMessage.getBytes("UTF-8"));
                buffer.flip();

                channel.write(buffer);
                LogbackUtil.logger(NIOClient.class, LoggerLevelEnum.DEBUG, "Connection. Socket Client send message [{}]", sentMessage);

                registerChannel(selector, channel, SelectionKey.OP_READ);
            } else {
                LogbackUtil.logger(NIOClient.class, LoggerLevelEnum.DEBUG, "Connection. Socket Client Connection Error");
                System.exit(1);
            }

        }

        if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            buffer.clear();

            int len;
            while ((len = channel.read(buffer)) > 0) {
                LogbackUtil.logger(NIOClient.class, LoggerLevelEnum.DEBUG, "Readable. Socket Client Read Message [{}]", new String(buffer.array(), 0, len, "UTF-8"));
            }
            if (len < 0) { channel.close(); }
            registerChannel(selector, channel, SelectionKey.OP_WRITE);
        }

        if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            buffer.clear();

            String sentMessage = "Writable. Hello Server, Write channel. " + System.currentTimeMillis();
            buffer.put(sentMessage.getBytes("UTF-8"));
            buffer.flip();

            channel.write(buffer);
            LogbackUtil.logger(NIOClient.class, LoggerLevelEnum.DEBUG, "Writable. Socket Client Sent Message [{}]", sentMessage);
            registerChannel(selector, channel, SelectionKey.OP_READ);

        }

    }

    private void registerChannel(Selector selector, SocketChannel channel, int opRead) throws IOException {
        if (channel == null) { return; }
        channel.configureBlocking(false);
        channel.register(selector, opRead);
    }

    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient();
        client.init("localhost");
        client.connection();
    }

}
