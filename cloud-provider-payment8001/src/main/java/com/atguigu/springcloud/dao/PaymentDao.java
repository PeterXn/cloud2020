package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entries.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Peter
 * @date 2022/9/15 0:31
 * @description Usage
 */
@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
