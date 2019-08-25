package com.dzg.nio;

import io.netty.util.concurrent.SingleThreadEventExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {
    public static void main(String[] args) throws IOException {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.stream().forEach(selectionKey -> {
                    try {
                        if (selectionKey.isConnectable()) {
                            SocketChannel client = (SocketChannel) selectionKey.channel();
                            if (client.isConnectionPending()) {
                                client.finishConnect();
                                ByteBuffer writeByte = ByteBuffer.allocate(512);
                                writeByte.put((LocalDateTime.now() + "连接成功").getBytes());
                                writeByte.flip();
                                client.write(writeByte);
                                ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                                executorService.submit(() -> {
                                    while (true) {
                                        try {
                                            writeByte.clear();
                                            InputStreamReader input = new InputStreamReader(System.in);
                                            BufferedReader br = new BufferedReader(input);
                                            String message = br.readLine();
                                            writeByte.put(message.getBytes());
                                            writeByte.flip();
                                            client.write(writeByte);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                            }
                            client.register(selector, SelectionKey.OP_READ);
                        } else if (selectionKey.isReadable()) {
                            System.out.println("进入读取状态");
                            SocketChannel client = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                            int count = client.read(byteBuffer);
                            if (count > 0) {
                                byteBuffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receivedMessage = String.valueOf(charset.decode(byteBuffer).array());
                                System.out.println("receivedMessage:" + receivedMessage);

                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
                selectionKeys.clear();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
