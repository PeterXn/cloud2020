package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Peter
 * @date 2022/9/22 11:05
 * @description Usage
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        LocalDateTime now = LocalDateTime.now();
        return "FlowLimitController.testA  ====>  " + now.toString();
    }

    @GetMapping("/testB")
    public String testB() {
        LocalDateTime now = LocalDateTime.now();
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "FlowLimitController.testB  ====>  " + now.toString();
    }


    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "testHotKeyHandler")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        //int i = 1 / 0;
        LocalDateTime now = LocalDateTime.now();
        return "FlowLimitController.testHotKey ==> " + now.toString();
    }

    public String testHotKeyHandler(@RequestParam(value = "p1", required = false) String p1,
                                    @RequestParam(value = "p2", required = false) String p2,
                                    BlockException exception) {
        LocalDateTime now = LocalDateTime.now();
        return "FlowLimitController.testHotKeyHandler ==> " + now.toString();
    }
}
