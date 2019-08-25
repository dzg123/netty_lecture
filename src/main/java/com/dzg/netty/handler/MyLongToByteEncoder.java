package com.dzg.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-07 18:52
 **/
@Slf4j
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        log.info("encoder invoked!");
        log.info(msg.toString());
        out.writeLong(msg);

    }


}
