package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author Peter
 * @date 2022/9/16 17:36
 * @description Usage
 */
@Service
public class PaymentService {

    public String paymentInfoOk(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + ", paymentInfoOk,Id: " + id + "\t" + "成功";
    }


    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfoTimeout(Integer id) {
        int timeNumber = 3000;
        //int i = 1/0;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + ", paymentInfoTimeout,Id: " + id + "\t" + "异常，耗时(秒)：" + timeNumber;
    }


    public String paymentInfoTimeoutHandler(Integer id) {

        return "线程池：" + Thread.currentThread().getName() + ", 系统繁忙或运行出错，请稍后再试。Id: " + id + "\t" + "，降级";
    }

    /**
     * 服务熔断
     * com/netflix/hystrix/HystrixCommandProperties.java
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {

        if (id < 0) {
            throw new RuntimeException("**** id不能是负数");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + uuid;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id")Integer id) {
        return Thread.currentThread().getName() + "\t" + "id不能是负数，请重试。Id为：" + id;
    }

}
