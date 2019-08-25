package com.dzg.grpc;

import com.dzg.netty.proto.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-21 22:23
 **/
@Slf4j
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase{
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        log.info("接受到客户端信息：{}",request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("李白dasdadasda").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        log.info("接受到客户端信息：{}",request.getAge());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(20).setName("张三").setCity("上海").build());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(20).setName("李四").setCity("上海").build());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(20).setName("王五").setCity("上海").build());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(20).setName("赵六").setCity("上海").build());
        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                log.info("onNext:{}",value.getAge());
            }

            @Override
            public void onError(Throwable t) {
            log.info(t.getMessage());

            }

            @Override
            public void onCompleted() {
                StudentResponse studentResponse = StudentResponse.newBuilder().setName("李白").setAge(22).setCity("上海").build();
                StudentResponse studentResponse1 = StudentResponse.newBuilder().setName("李白").setAge(22).setCity("上海").build();
                StudentResponseList responseList = StudentResponseList.newBuilder().addStudentResponse(studentResponse).addStudentResponse(studentResponse1).build();
                responseObserver.onNext(responseList);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                log.info(value.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                log.info(t.getMessage());

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();

            }
        };
    }
}
