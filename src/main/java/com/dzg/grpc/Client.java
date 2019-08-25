package com.dzg.grpc;

import com.dzg.netty.proto.MyRequest;
import com.dzg.netty.proto.MyResponse;
import com.dzg.netty.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-22 19:05
 **/
public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        MyResponse response = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("张三").build());
        System.out.println(response.getRealname());
    }
}
