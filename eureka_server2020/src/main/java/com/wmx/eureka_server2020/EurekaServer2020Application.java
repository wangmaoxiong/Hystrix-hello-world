package com.wmx.eureka_server2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @EnableEurekaServer ：开启 eureka server ，否则 eureka 服务不会生效.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer2020Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer2020Application.class, args);
    }

}
