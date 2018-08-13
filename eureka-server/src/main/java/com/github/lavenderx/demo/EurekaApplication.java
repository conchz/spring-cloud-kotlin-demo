package com.github.lavenderx.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * SpringCloud服务注册与发现Eureka以及注册源码解析: http://blog.51cto.com/qinbin/2067728
 * 划分微服务边界的5个特征: https://www.jdon.com/49426
 * https://github.com/charlesvhe/spring-cloud-practice
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
