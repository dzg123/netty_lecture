package com.dzg.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-30 23:09
 **/
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8899));
        socketChannel.configureBlocking(true);
        String fileName = "D:\\soft\\jdk-8u131-linux-x64.tar.gz";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();
        long transfer = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总字节数：" + transfer + ",耗时：" + (System.currentTimeMillis() - startTime));
        fileChannel.close();

    }
}
