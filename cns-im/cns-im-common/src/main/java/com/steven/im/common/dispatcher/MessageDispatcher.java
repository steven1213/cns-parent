package com.steven.im.common.dispatcher;

import com.google.gson.Gson;
import com.steven.im.common.codec.Invocation;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author steven.cao
 */
@ChannelHandler.Sharable
public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> {
    private static Gson gson = new Gson();
//    @Resource
    private MessageHandlerContainer messageHandlerContainer;

    private final ExecutorService executor = Executors.newFixedThreadPool(200);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Invocation invocation) throws Exception {
        MessageHandler messageHandler = messageHandlerContainer.getMessageHandler(invocation.getMsgType());
        // 获得  MessageHandler 处理器 的消息类
        Class<? extends Message> messageClass = MessageHandlerContainer.getMessageClass(messageHandler);
        // 解析消息
        Message message = gson.fromJson(String.valueOf(invocation), messageClass);
        // 执行逻辑
        executor.submit(() -> {
            // noinspection unchecked
            messageHandler.execute(channelHandlerContext.channel(), message);
        });
    }
}
