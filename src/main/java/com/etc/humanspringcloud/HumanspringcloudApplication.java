package com.etc.humanspringcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HumanspringcloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanspringcloudApplication.class, args);
    }

}
