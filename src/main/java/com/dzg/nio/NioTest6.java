package com.dzg.nio;


import java.nio.ByteBuffer;

public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);
            
        }
        byteBuffer.position(2);
        byteBuffer.limit(6);
        ByteBuffer sliceBuffer = byteBuffer.slice();
        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get();
            sliceBuffer.put(i, (byte) (2 * b));

        }
        byteBuffer.clear();
        while(byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());

        }
    }
}
