package com.atguigu.springcloud.feign;

import com.atguigu.springcloud.entries.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Peter
 * @date 2022/9/16 10:55
 * @description Usage
 */
@Component
@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignService {


    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);


    @GetMapping(value = "/payment/timeout")
    public String paymentFeignTimeout();
}
