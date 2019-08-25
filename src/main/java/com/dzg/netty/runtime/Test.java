package com.dzg.netty.runtime;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-03-31 17:41
 **/
public class Test {
    public static void main(String[] args) {
        int result = Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(result);
    }
}
