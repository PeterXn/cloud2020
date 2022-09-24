package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.MessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Peter
 * @date 2022/9/21 1:24
 * @description Usage
 */
@RestController
public class SendMessageController {

    @Resource
    private MessageProvider messageProvider;


    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }

}
