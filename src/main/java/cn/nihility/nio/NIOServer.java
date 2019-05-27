package cn.nihility.nio;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by yzx on 2019/5/20.
 */
public class NIOServer {

    private Selector selector;

    public void initServer(int port) throws IOException {
        // 获得一个 ServerSocketChannel 通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 把该通道的 ServerSocket 绑定到具体的端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        // 获取通道管理器
        this.selector = Selector.open();

        /* 通道管理器和该通道绑定，注册 SelectingKey.OP_ACCEPT 事件 */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    public void listen() throws IOException {
        LogbackUtil.logger(NIOServer.class, LoggerLevelEnum.DEBUG, "Server start success.");
        // 轮询 Selector
        while (true) {
            // 注册事件到达后，方法返回，否则该方法会阻塞
            int select = selector.select();
            if (0 == select) { continue; }

            // 获取 Selector 中的选中项迭代器，选中项为注册时间
            Iterator<SelectionKey> keyIterator = this.selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                // 删除已选 key，防止重复
                keyIterator.remove();

                ServerSocketChannel selectableChannel;
                if (selectionKey.isAcceptable()) { // 客户端请求连接事件
                    selectableChannel = (ServerSocketChannel) selectionKey.channel();

                    // 获得和客户端的通道
                    SocketChannel channel = selectableChannel.accept();
                    // 设置为非阻塞
                    channel.configureBlocking(false);

                    // 发送消息给客户端
                    channel.write(ByteBuffer.wrap(new String("Server call back message : " + System.currentTimeMillis()).getBytes("UTF-8")));

                    // 和客户端连接成功后，为了可以接受到客户端的信息，给通道设置读权限
                    channel.register(this.selector, SelectionKey.OP_READ);

                } else if (selectionKey.isReadable()){
                    read(selectionKey);
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        // 服务器可读消息
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取缓存区
        ByteBuffer buffer = ByteBuffer.allocate(512);
        channel.read(buffer);

        byte[] array = buffer.array();
        String recMessage = new String(array, "UTF-8");
        LogbackUtil.logger(NIOServer.class, LoggerLevelEnum.DEBUG, "Rec client message : [{}]", recMessage);

        ByteBuffer outBuffer = ByteBuffer.wrap(recMessage.getBytes("UTF-8"));
        channel.write(outBuffer); // 回送消息给 client
    }

    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(60000);
        server.listen();
    }
}
