package com.wmx.eureka_server2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @EnableEurekaServer ：开启 eureka server ，否则 eureka 服务不会生效.
 * @EnableHystrixDashboard : 开启 Hystrix 仪表盘，否则 hystrix 仪表板不开启
 */
@SpringBootApplication
@EnableEurekaServer
@EnableHystrixDashboard
public class EurekaServer2020Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer2020Application.class, args);
    }

}
