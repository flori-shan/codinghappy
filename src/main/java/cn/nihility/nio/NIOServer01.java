package cn.nihility.nio;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yzx on 2019/5/20.
 */
public class NIOServer01 {

    private final static int BUFFER_SIZE = 1024;
    private ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

    private final static int PORT = 60000;
    private Selector selector;

    public void init(int port) throws IOException {
        logger("Listening On Port [{}]", port);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));

        this.selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    public void listen() throws IOException, InterruptedException {
        while (true) {
            int readyChannels = selector.select();
            if (0 == readyChannels) { continue; }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                keyIterator.remove();
                handleKey(selectionKey);
            }
            Thread.sleep(1000);
        }
    }

    private void handleKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = socketChannel.accept();

            registerChannel(selector, channel, SelectionKey.OP_READ);

            if (channel.isConnected()) {
                String message = "Socket server reply message. | " + System.currentTimeMillis();

                buffer.clear();
                buffer.put(message.getBytes("UTF-8"));
                buffer.flip();

                channel.write(buffer);
            }
        }

        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            buffer.clear();

            int len;
            while ((len = socketChannel.read(buffer)) > 0) {
                logger("Readable Server Socket Rec message [{}]", new String(buffer.array(), 0, len, "UTF-8"));
            }
            if (len < 0) {
                socketChannel.close();
            }

            registerChannel(selector, socketChannel, SelectionKey.OP_WRITE);

        }

        // 通道的可写事件就绪
        if (key.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            buffer.clear(); // 清空缓冲区

            // 准备发送的数据
            String message_from_server = "Writable Socket Server. Hello, Client ... " + socketChannel.getLocalAddress();
            buffer.put(message_from_server.getBytes("UTF-8"));
            buffer.flip();
            socketChannel.write(buffer);
            logger("Writable Socket Server. Sent Message [{}]", message_from_server);

            // SocketChannel通道的可写事件注册到Selector中
            registerChannel(selector, socketChannel, SelectionKey.OP_READ);
        }
    }

    private void registerChannel(Selector selector, SocketChannel channel, int opRead) throws IOException {
        if (channel == null) { return; }

        channel.configureBlocking(false);
        channel.register(selector, opRead);
    }

    private void logger(String message, Object... args) {
        LogbackUtil.logger(NIOServer01.class, LoggerLevelEnum.DEBUG, message, args);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NIOServer01 server01 = new NIOServer01();
        server01.init(PORT);
        server01.listen();
    }

}
