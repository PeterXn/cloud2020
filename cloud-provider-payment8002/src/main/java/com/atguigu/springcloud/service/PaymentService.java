package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entries.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Peter
 * @date 2022/9/15 0:48
 * @description Usage
 */

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
