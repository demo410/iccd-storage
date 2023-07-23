package com.iccd.storage.stream.core;

import com.google.common.collect.Maps;
import com.iccd.storage.core.exception.StorageFrameworkException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractStreamProducer implements IStreamProducer{

    @Autowired
    private Map<String, MessageChannel> channelMap;
    @Override
    public boolean sendMessage(String channelName, Object deploy) {
        return sendMessage(channelName, deploy, Maps.newHashMap());
    }

    @Override
    public boolean sendMessage(String channelName, Object deploy, Map<String, Object> headers) {
        if (StringUtils.isBlank(channelName) || Objects.isNull(deploy)) {
            throw new StorageFrameworkException("the channelName or deploy can not be empty!");
        }
        if (MapUtils.isEmpty(channelMap)) {
            throw new StorageFrameworkException("the channelMap can not be empty!");
        }
        MessageChannel channel = channelMap.get(channelName);
        if (Objects.isNull(channel)) {
            throw new StorageFrameworkException("the channel named" + channelName + " can not be found!");
        }

        Message message = MessageBuilder.createMessage(deploy, new MessageHeaders(headers));
        preSend(message);
        boolean result = channel.send(message);
        afterSend(message, result);
        return false;
    }

    /**
     * 发送消息的后置钩子函数
     *
     * @param message
     * @param result
     */
    protected abstract void afterSend(Message message, boolean result);

    /**
     * 发送消息的前置钩子函数
     *
     * @param message
     */
    protected abstract void preSend(Message message);
}
