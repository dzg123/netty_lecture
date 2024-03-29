package com.dzg.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NioTest11 {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);
        int messageLength = 2 + 3 + 4;
        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);
        SocketChannel socketChannel = serverSocketChannel.accept();
        while (true) {
            int bytesRead = 0;
            while (bytesRead < messageLength) {
                long read = socketChannel.read(byteBuffers);
                bytesRead += read;
                System.out.println("byteRead:" + bytesRead);
                Arrays.asList(byteBuffers).stream().map(buffer -> "position:" + buffer.position() + ",limit:" + buffer.limit())
                        .forEach(System.out::println);


            }
            Arrays.asList(byteBuffers).stream().forEach(byteBuffer -> byteBuffer.flip());
            long writeByte = 0;
            while (writeByte < messageLength) {
                long write = socketChannel.write(byteBuffers);
                writeByte += write;
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());


        }

    }
}
