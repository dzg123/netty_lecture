package com.dzg.nio;


import java.nio.ByteBuffer;
//ByteBuffer类型化的put和get方法
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.put((byte) 1);
        byteBuffer.putChar('a');
        byteBuffer.putDouble(100.222);
        byteBuffer.putInt(11);
        byteBuffer.flip();
        System.out.println( byteBuffer.get());
        byteBuffer.getChar();
        byteBuffer.getDouble();
        byteBuffer.getInt();
    }
}
