package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.entries.CommonResult;
import com.atguigu.springcloud.entries.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Peter
 * @date 2022/9/22 17:49
 * @description Usage
 */
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
