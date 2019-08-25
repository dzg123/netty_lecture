package com.dzg.netty.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-08 15:24
 **/
@Slf4j
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();
        log.info("客户端接收到的消息：\n长度：{}，\n内容：{}，\n,客户端接收到的消息数量：{}\n", length,
                new String(content, Charset.forName("utf-8")), ++count);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            String messageToBeSend = "send from client";
            byte[] content = messageToBeSend.getBytes("utf-8");
            int length = content.length;
            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(length);
            personProtocol.setContent(content);
            ctx.writeAndFlush(personProtocol);
        }
    }
}
