package com.dzg.netty.thirdexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-12 16:32
 **/
public class MyChatClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            Channel channel = bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer())
                    .connect("localhost", 8899).sync().channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                channel.writeAndFlush(br.readLine() + "\r\n");
            }
//            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
