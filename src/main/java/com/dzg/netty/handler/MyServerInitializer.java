package com.dzg.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-11 21:55
 **/
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyLongToByteEncoder());
        pipeline.addLast(new MyByteToLongDecoder2());
        pipeline.addLast(new MyLongToStringDecoder());
        pipeline.addLast(new MyServerHandler());
    }
}
