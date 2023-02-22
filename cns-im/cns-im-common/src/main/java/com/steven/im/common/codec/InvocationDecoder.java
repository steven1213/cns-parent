package com.steven.im.common.codec;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author steven.cao
 */
public class InvocationDecoder extends ByteToMessageDecoder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static Gson gson = new Gson();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 标记当前读取位置
        byteBuf.markReaderIndex();
        if (byteBuf.readableBytes() <= 4) {
            return;
        }
        // 读取长度
        int length = byteBuf.readInt();
        if (length < 0) {
            throw new CorruptedFrameException("negative length:" + length);
        }

        // 如果message不够可读,则退回到原读取位置
        if (byteBuf.readableBytes() < length) {
            byteBuf.resetReaderIndex();
            return;
        }
        // 读取内容
        byte[] content = new byte[length];
        byteBuf.readBytes(content);
        // 解析成Invocation
        Invocation invocation = gson.fromJson(new String(content), Invocation.class);
        list.add(invocation);
        logger.info("[decode][连接({}) 解析到一条消息({})]", channelHandlerContext.channel().id(), invocation.toString());
    }
}
