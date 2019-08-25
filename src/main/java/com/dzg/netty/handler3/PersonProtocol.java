package com.dzg.netty.handler3;

import lombok.Data;

/**
 * @program: netty_lecture
 * @description:
 * @author: dzg
 * @create: 2019-04-08 14:58
 **/
@Data
public class PersonProtocol {
    private int length;
    private byte[] content;
}
