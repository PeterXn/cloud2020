package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Peter
 * @date 2022/9/23 16:52
 * @description Usage
 */
@Mapper
public interface OrderDao
{
    /**
     * 1 新建订单
     * @param order
     */
    void create(Order order);

    /**
     * 2 修改订单状态，从零改为1
     * @param userId
     * @param status
     */
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
