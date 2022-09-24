package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Peter
 * @date 2022/9/20 1:00
 * @description Usage
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();

        routes.route("path_route_baidu",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();

        routes.route("path_route_bing",
                r -> r.path("/bing").uri("https://cn.bing.com/")).build();

        return routes.build();
    }

}
