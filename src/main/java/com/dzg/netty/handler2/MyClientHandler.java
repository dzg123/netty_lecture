package com.dzg.netty.handler2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-08 14:21
 **/
@Slf4j
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ByteBuf buffer = Unpooled.copiedBuffer("send from client", Charset.forName("utf-8"));
            ctx.writeAndFlush(buffer);

        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytebuffer = new byte[msg.readableBytes()];
        msg.readBytes(bytebuffer);
        String message = new String(bytebuffer, Charset.forName("utf-8"));
        log.info("客户端收到的消息：{}", message);
        log.info("客户端收到的消息数量：{}", ++count);
    }
}
