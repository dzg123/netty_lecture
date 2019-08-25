package com.dzg.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                        .setName("李白").setAge(22).setAddress("上海").build();
        byte[] student2ByteArray = student.toByteArray();
        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println(student2);
        System.out.println(student2.getName());
    }
}
