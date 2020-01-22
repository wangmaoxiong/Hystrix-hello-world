package com.wmx.echystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableCircuitBreaker ：表示开启断路器模式，不写时只要导入了 hystrix 依赖默认也是开启的。可以不写。
 * @EnableEurekaClient ：只要导入了 eureka-client 依赖，则默认也是开启 eureka 客户端的。可以不写。
 * @EnableFeignClients :开启 feign 功能，否则默认不开启
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
public class EcHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcHystrixApplication.class, args);
    }

    /**
     * 微服务之间的调用，这里直接使用 org.springframework.web.client.RestTemplate 进行调用
     * RestTemplate 它默认是没有交由 spring 容器管理的，需要自己添加到容器，然后才能使用。
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
