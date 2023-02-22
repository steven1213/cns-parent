package com.steven.im.common.dispatcher;

import com.steven.im.common.type.CnsIMMsgType;
import io.netty.channel.Channel;


/**
 * @author steven.cao
 */
public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     *
     * @param channel 通道
     * @param message 消息
     */
    void execute(Channel channel, T message);

    /**
     * @return 消息类型。即每个Message实现类上的TYPE静态字段
     */
    CnsIMMsgType getMsgType();
}
