
package com.dzg.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < intBuffer.capacity()/2; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            intBuffer.put(randomNumber);
        }
        System.out.println("limit:"+intBuffer.limit());
        intBuffer.flip();
        System.out.println("AfterLimit:"+intBuffer.limit());

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());

        }

    }
}
