package com.github.lavenderx.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ServicebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicebApplication.class, args);
    }
}
