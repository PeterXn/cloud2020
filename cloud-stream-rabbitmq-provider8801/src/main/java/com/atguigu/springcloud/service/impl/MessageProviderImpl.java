package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Peter
 * @date 2022/9/21 1:11
 * @description Usage
 */
@EnableBinding(Source.class) //定义消息的推送管道
@Slf4j
public class MessageProviderImpl implements MessageProvider {

    /**
     * 消息发送管道
     */
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String id = UUID.randomUUID().toString();
        LocalDateTime dateTime = LocalDateTime.now();
        String msg = dateTime.toString() + " " + id;

        output.send(MessageBuilder.withPayload(msg).build());

        log.info("**** 发送的消息为：{}", msg);

        return msg;
    }
}
