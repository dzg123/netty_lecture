package com.dzg.nio;


import java.nio.ByteBuffer;

public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);

        }
        ByteBuffer byteBuffer1 = byteBuffer.asReadOnlyBuffer();
        byteBuffer1.position(0);
        byteBuffer1.put((byte) 1);

    }
}
