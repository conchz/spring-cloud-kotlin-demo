package com.github.lavenderx.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://springcloud.cc/spring-cloud-consul.html
 * https://blog.csdn.net/zjcjava/article/details/78608892
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulApplication {

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class, args);
    }
}
