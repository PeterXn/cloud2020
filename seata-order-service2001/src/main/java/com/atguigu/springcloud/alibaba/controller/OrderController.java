package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.domain.CommonResult;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Peter
 * @date 2022/9/23 16:59
 * @description Usage
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        order.setUserId(1L);
        order.setProductId(1L);
        order.setCount(10);
        order.setMoney(BigDecimal.valueOf(100L));

        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
