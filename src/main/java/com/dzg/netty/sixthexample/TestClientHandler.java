package com.dzg.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-14 15:07
 **/
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int i = new Random().nextInt(3);
        MyDataInfo.MyMessage message = null;
        switch (i) {
            case 0:
                message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                        .setPerson(MyDataInfo.Person.newBuilder().setName("李白").setAge(22).setAddress("上海").build())
                        .build();
                break;
            case 1:
                message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.DogType)
                        .setDog(MyDataInfo.Dog.newBuilder().setName("狗").setAge(2).build())
                        .build();
                break;
            case 2:
                message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.CatType)
                        .setCat(MyDataInfo.Cat.newBuilder().setName("猫").setCity("上海").build())
                        .build();
                break;
        }

                ctx.channel().writeAndFlush(message);
    }
}
