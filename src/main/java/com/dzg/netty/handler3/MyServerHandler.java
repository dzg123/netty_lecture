package com.dzg.netty.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-08 15:12
 **/
@Slf4j
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();
        log.info("服务端接收到的数据：\n长度：{}，\n内容：{},\n服务端接收到的消息数量：{}\n", length,
                new String(content, Charset.forName("utf-8")), ++count);
        String responseMessage = UUID.randomUUID().toString();
        int responseLength = responseMessage.getBytes("utf-8").length;
        byte[] responseContent = responseMessage.getBytes("utf-8");
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(responseLength);
        personProtocol.setContent(responseContent);
        ctx.writeAndFlush(personProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage());
        ctx.close();

    }

}
