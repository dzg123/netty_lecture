package com.dzg.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try {
                byte[] bytes = new byte[4096];
                while (true) {
                    int count = dataInputStream.read(bytes);
                    if (count == -1) {
                        break;

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
