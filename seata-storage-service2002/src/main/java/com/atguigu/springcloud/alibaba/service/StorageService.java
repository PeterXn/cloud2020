package com.atguigu.springcloud.alibaba.service;

/**
 * @author Peter
 * @date 2022/9/23 17:53
 * @description Usage
 */

public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
