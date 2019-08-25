package com.dzg.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class NioTest13 {
    public static void main(String[] args) throws Exception {
        String inputFile = "NioTest13_in.txt";
        String outputFile = "NioTest13_out.txt";
        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");
        long length = new File(inputFile).length();
        FileChannel inputRandomAccessFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputRandomAccessFileChannel = outputRandomAccessFile.getChannel();
        MappedByteBuffer inputData = inputRandomAccessFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);
        System.out.println("=======================");
        Charset.availableCharsets().forEach((k, v) -> {
            System.out.println(k + "," + v);
        });
        System.out.println("=======================");
        Charset charset = Charset.forName("utf-8");
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = decoder.decode(inputData);
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        outputRandomAccessFileChannel.write(byteBuffer);
    }
}
