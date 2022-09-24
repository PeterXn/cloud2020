package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author Peter
 * @date 2022/9/17 21:53
 * @description Usage
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfoOk(Integer id) {
        return "PaymentFallbackService...paymentInfoOk...异常处理";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "PaymentFallbackService...paymentInfoTimeout...异常处理";
    }
}
