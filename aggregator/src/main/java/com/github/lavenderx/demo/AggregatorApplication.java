package com.github.lavenderx.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * 使用Zuul构建微服务网关-路由端点与路由配置详解: http://blog.51cto.com/1754966750/1958422
 * Spring Cloud各组件超时总结: https://blog.csdn.net/qq_24267619/article/details/78653095
 * Feign Doc: https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
 * Spring Cloud Feign常见问题: https://blog.csdn.net/jeffleo/article/details/71493208
 * spring-cloud服务网关中的Timeout设置: https://deanwangpro.com/2018/04/13/zuul-hytrix-ribbon-timeout/
 * 服务网关Zuul高级篇: http://www.ityouknow.com/springcloud/2018/01/20/spring-cloud-zuul.html
 * Spring Cloud Zuul的fallback优化: http://blog.didispace.com/spring-cloud-zuul-fallback-improve/
 *
 * @see org.springframework.cloud.netflix.zuul.filters.route.support.AbstractRibbonCommand
 * @see com.netflix.client.config.DefaultClientConfigImpl
 * @see com.netflix.hystrix.HystrixCommand
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
