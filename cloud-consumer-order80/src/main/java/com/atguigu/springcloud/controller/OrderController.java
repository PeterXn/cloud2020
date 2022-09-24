package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entries.CommonResult;
import com.atguigu.springcloud.entries.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Peter
 * @date 2022/9/15 9:58
 * @description Usage
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL_SINGLE = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {

        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        // http://localhost/consumer/payment/get/11

        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String getPaymentZipkin() {
        // http://localhost/consumer/payment/get/11

        return restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin/", String.class);
    }

}
