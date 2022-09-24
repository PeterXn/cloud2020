package com.atguigu.springcloud.controller;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @author Peter
 * @date 2022/9/21 9:40
 * @description Usage
 */
@Controller
@EnableBinding(Sink.class)
@Slf4j
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message,
                      @Header(AmqpHeaders.CHANNEL) Channel channel,
                      @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {
        if (message.getPayload() == null ||
                StringUtils.isBlank(message.getPayload())) {
            log.info("当前消费者是：[{}],\t---->接受到的消息是：[为空]", serverPort);
        }
        try {
            log.info("当前消费者是：[{}],\t---->接受到的消息是：[{}], 消息deliveryTag是：[{}]", serverPort, message.getPayload(), deliveryTag);
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
