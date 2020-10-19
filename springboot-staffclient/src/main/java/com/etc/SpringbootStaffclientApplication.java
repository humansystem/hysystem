package com.etc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringbootStaffclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStaffclientApplication.class, args);
    }

}
