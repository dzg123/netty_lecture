package com.dzg.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-14 14:58
 **/
@Slf4j
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        if (msg.getDataType() == MyDataInfo.MyMessage.DataType.PersonType) {
            log.info(msg.getPerson().getName());
            log.info(msg.getPerson().getAddress());
        }else if(msg.getDataType() == MyDataInfo.MyMessage.DataType.DogType){
            log.info(msg.getDog().getName());
            log.info(String.valueOf(msg.getDog().getAge()));

        }else {
            log.info(msg.getCat().getName());
            log.info(msg.getCat().getCity());

        }

    }
}
