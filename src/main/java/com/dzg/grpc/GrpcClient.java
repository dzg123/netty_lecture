package com.dzg.grpc;


import com.dzg.netty.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

@Slf4j
public class GrpcClient {
    private final ManagedChannel channel;
    private final StudentServiceGrpc.StudentServiceBlockingStub blockingStub;
    private final StudentServiceGrpc.StudentServiceStub stub;

    public GrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));

    }

    GrpcClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        stub = StudentServiceGrpc.newStub(channel);

    }

    public void shutdown() throws InterruptedException {
//        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greet(String username) {
        log.info("will try to greet:{}...", username);
        MyRequest request = MyRequest.newBuilder().setUsername(username).build();
        MyResponse response;
        try {
            response = blockingStub.getRealNameByUsername(request);

        } catch (StatusRuntimeException ex) {
            log.info("RPC failed:{}", ex);
            return;
        }
        log.info("Greeting:{}", response.getRealname());

    }

    public static void main(String[] args) throws InterruptedException {
        GrpcClient client = new GrpcClient("localhost", 50051);
        try {
            String username = "dzg";
            if (args.length > 0) {
                username = args[0]; /* Use the arg as the name to greet if provided */
            }
            client.greet(username);
//            log.info("-------------------");
//            Iterator<StudentResponse> students = client.blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(22).build());
//            while (students.hasNext()) {
//                log.info(students.next().getName());
//
//            }
//            log.info("-------------------");
//            StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
//                @Override
//                public void onNext(StudentResponseList value) {
//                    value.getStudentResponseList().forEach(e -> {
//                        log.info(e.getName());
//                        log.info(e.getCity());
//                        log.info(String.valueOf(e.getAge()));
//                        log.info("**********");
//                    });
//                }
//
//                @Override
//                public void onError(Throwable t) {
//                    log.info(t.getMessage());
//                }
//
//                @Override
//                public void onCompleted() {
//                    log.info("completed!");
//                }
//            };
//            StreamObserver<StudentRequest> studentRequestStreamObserver = client.stub.getStudentsWrapperByAges(studentResponseListStreamObserver);
//            studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(22).build());
//            studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(33).build());
//            studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(44).build());
//            studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(55).build());
//            studentRequestStreamObserver.onCompleted();
//            StreamObserver<StreamRequest> streamRequestStreamObserver = client.stub.biTalk(new StreamObserver<StreamResponse>() {
//                @Override
//                public void onNext(StreamResponse value) {
//                    log.info(value.getResponseInfo());
//
//                }
//
//                @Override
//                public void onError(Throwable t) {
//                    log.info(t.getMessage());
//                }
//
//                @Override
//                public void onCompleted() {
//                    log.info("completed");
//                }
//            });
//            for (int i = 0; i <10 ; i++) {
//                streamRequestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
//                Thread.sleep(1000);
//
//            }
//            Thread.sleep(5000);
//
////            client.greet(username);
////            client.greet(username);
////            client.greet(username);
////            client.greet(username);
////            client.greet(username);
//
        } finally {
            client.shutdown();
        }

    }

}
