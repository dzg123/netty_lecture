package com.dzg.netty.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-08 14:59
 **/
@Slf4j
public class MyPersonDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("MyPersonDecoder decode invoked!");
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(length);
        personProtocol.setContent(content);
        out.add(personProtocol);
    }
}
