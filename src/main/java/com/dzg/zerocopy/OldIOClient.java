package com.dzg.zerocopy;

import com.sun.org.apache.xerces.internal.util.SymbolTable;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

public class OldIOClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8899);
        String fileName = "D:\\soft\\jdk-8u131-linux-x64.tar.gz";
        InputStream inputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[4096];
        long readCount;
        long total = 0;
        long startTime = System.currentTimeMillis();
        while ((readCount = inputStream.read(bytes)) >= 0) {
            total += readCount;
            dataOutputStream.write(bytes);
        }
        System.out.println("发送总字节数："+total+",耗时："+ (System.currentTimeMillis()-startTime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();


    }
}
