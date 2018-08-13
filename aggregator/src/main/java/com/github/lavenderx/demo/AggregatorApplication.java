package com.github.lavenderx.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * http://blog.51cto.com/1754966750/1958422
 * https://blog.csdn.net/qq_24267619/article/details/78653095
 * https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
 * Spring Cloud Feign常见问题: https://blog.csdn.net/jeffleo/article/details/71493208
 */
@SpringCloudApplication
@EnableFeignClients
@EnableZuulProxy
public class AggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregatorApplication.class, args);
    }

    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }
}
