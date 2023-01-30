package com.steven.im.common.codec;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dr.panda
 */
public class InvocationEncoder extends MessageToByteEncoder<Invocation> {
    private static Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Invocation invocation, ByteBuf byteBuf) throws Exception {
        // 将Invocation转换成byte[]数组
        byte[] content = gson.toJson(invocation).getBytes();
        // 写入length
        byteBuf.writeInt(content.length);
        // 写入内容
        byteBuf.writeBytes(content);
        logger.info("[encode][连接({}) 编码了一条消息({})]", channelHandlerContext.channel().id(), invocation.toString());
    }
}
