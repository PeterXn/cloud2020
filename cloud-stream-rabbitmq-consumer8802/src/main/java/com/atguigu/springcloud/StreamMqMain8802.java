package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Peter
 * @date 2022/9/21 9:38
 * @description Usage
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMqMain8802 {

    public static void main(String[] args) {
        SpringApplication.run(StreamMqMain8802.class, args);
    }
}
