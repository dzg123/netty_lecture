package com.dzg.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-12 14:19
 **/
@Slf4j
public class MyClientHandler extends SimpleChannelInboundHandler<String>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
      log.info(ctx.channel().remoteAddress()+","+msg);
      ctx.writeAndFlush("from client:"+ LocalDateTime.now());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
