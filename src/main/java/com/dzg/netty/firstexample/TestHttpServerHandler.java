package com.dzg.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;


import java.net.URI;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-11 14:09
 **/
@Slf4j
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
            log.info("class:{}",msg.getClass());
            log.info("remoteAddress:{}",ctx.channel().remoteAddress());
            HttpRequest httpRequest = (HttpRequest) msg;
            log.info("请求方法名：{}",httpRequest.method().name());
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){
                log.info("浏览器获取图标请求");
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            ctx.writeAndFlush(response);
            ctx.channel().close();
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved:{}",ctx.handler());
        super.handlerRemoved(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channelUnregistered");

        super.channelUnregistered(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channelRegistered");

        super.channelRegistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
       log.info("handlerAdded:{}",ctx.handler());

        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelInactive:{}",ctx.channel());
        super.channelInactive(ctx);
    }
}
