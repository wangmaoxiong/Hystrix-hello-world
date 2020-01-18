package com.wmx.eureka_client2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EurekaClient2020Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient2020Application.class, args);
    }

}
