package com.dzg.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        ServerSocket socket = socketChannel.socket();
        socket.setReuseAddress(true);
        socket.bind(new InetSocketAddress(8899));
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel channel = socketChannel.accept();
            channel.configureBlocking(true);
            int readCount = 0;
            while (-1 != readCount) {
                try {
                    readCount = channel.read(byteBuffer);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                byteBuffer.rewind();
            }

        }

    }
}
