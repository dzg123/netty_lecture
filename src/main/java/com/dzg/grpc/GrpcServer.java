package com.dzg.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GrpcServer {
    private Server server;

    private void start()  {
        try {
            int port = 50051;
            server = ServerBuilder.forPort(port).addService(new StudentServiceImpl()).build().start();
            log.info("服务器开始运行，监听端口为{}", port);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                log.error("*** shutting down gRPC server since JVM is shutting down");
                GrpcServer.this.stop();
                log.info("*** server shut down");
            }));
        } catch (IOException e) {
            log.info(e.getMessage());
        }

    }

    private void stop() {
        if (server != null) {
            server.shutdown();

        }
    }
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
         GrpcServer server = new GrpcServer();
        server.start();
        server.blockUntilShutdown();

    }
}
