package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entries.CommonResult;
import com.atguigu.springcloud.entries.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Peter
 * @date 2022/9/15 0:53
 * @description Usage
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据成功，端口：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败，端口：" + serverPort, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("****查询结果：" + result);

        if (result != null) {
            return new CommonResult(200, "查询成功，端口：" + serverPort, result);
        } else {
            return new CommonResult(444, "没有对应记录，查询ID：" + id + "，端口：" + serverPort, null);
        }
    }


    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String ele : services) {
            log.info("****element " + ele);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/timeout")
    public String paymentFeignTimeout() {
        System.out.println("PaymentController.paymentFeignTimeout");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "交易已超时，当前端口为：" + serverPort;
    }


    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        System.out.println("PaymentController.paymentZipkin");
        return "hi, this is PaymentController.paymentZipkin, serverPort：" + serverPort;
    }

}
