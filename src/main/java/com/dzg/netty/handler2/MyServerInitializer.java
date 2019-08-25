package com.dzg.netty.handler2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-08 13:59
 **/
@Slf4j
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        log.info("{}初始化channel",this);
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyServerHandler());
    }
}
