package com.dzg.nio;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");
        FileChannel inChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (true) {
            byteBuffer.clear();//必须有
            int read = inChannel.read(byteBuffer);
            System.out.println("read" + read);
            if (read == -1) {
                break;

            }
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }
        inChannel.close();
        outputChannel.close();

    }

}
