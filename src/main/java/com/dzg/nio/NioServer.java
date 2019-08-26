package com.dzg.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NioServer {
    private static Map<String, SocketChannel> clientMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.socket().bind(new InetSocketAddress(8899));
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            try {
                int numbers = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                            String key = "【" + UUID.randomUUID() + "】";
                            clientMap.put(key, client);


                        } else if (selectionKey.isReadable()) {
                            client = (SocketChannel) selectionKey.channel();
                            int byteRead = 0;
                            ByteBuffer buffer = ByteBuffer.allocate(512);
                            buffer.clear();
                            int read = client.read(buffer);
                            if (read > 0) {
                                buffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receiveMessage = String.valueOf(charset.decode(buffer).array());
                                log.info("{}:{}", client, receiveMessage);
                                Set<Map.Entry<String, SocketChannel>> entries = clientMap.entrySet();
                                String clientKey = null;
                                for (Map.Entry<String, SocketChannel> entry : entries) {
                                    if (entry.getValue() == client) {
                                        clientKey = entry.getKey();
                                        break;
                                    }
                                }
                                for(Map.Entry<String,SocketChannel> entry: entries){
                                    SocketChannel value = entry.getValue();
                                    buffer.clear();
                                    buffer.put((clientKey + ":" + receiveMessage).getBytes());
                                    buffer.flip();
                                    value.write(buffer);

                                }

                            }


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                selectionKeys.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
