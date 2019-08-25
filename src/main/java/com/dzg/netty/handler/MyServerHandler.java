package com.dzg.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-11 22:24
 **/
@Slf4j
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info(ctx.channel().remoteAddress() + "," + msg);
        ctx.writeAndFlush(654321L);
//        new ThreadPoolExecutor()
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
