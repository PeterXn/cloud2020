package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.entries.CommonResult;
import com.atguigu.springcloud.entries.Payment;
import org.springframework.stereotype.Component;

/**
 * @author Peter
 * @date 2022/9/22 17:50
 * @description Usage
 */
@Component
public class PaymentFallbackService implements PaymentService{

    @Override
    public CommonResult<Payment> paymentSQL(Long id)
    {
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));
    }

}
