package com.dzg.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-07 23:03
 **/
@Slf4j
public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("MyByteToLongDecoder2 decode invoked!");
        out.add(in.readLong());

    }
}
