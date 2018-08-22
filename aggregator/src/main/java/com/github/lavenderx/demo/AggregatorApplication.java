package com.github.lavenderx.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 使用Zuul构建微服务网关-路由端点与路由配置详解: http://blog.51cto.com/1754966750/1958422
 * <p>
 * Spring Cloud各组件超时总结: https://blog.csdn.net/qq_24267619/article/details/78653095
 * <p>
 * Feign Doc: https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
 * <p>
 * Spring Cloud Feign常见问题: https://blog.csdn.net/jeffleo/article/details/71493208
 * <p>
 * spring-cloud服务网关中的Timeout设置: https://deanwangpro.com/2018/04/13/zuul-hytrix-ribbon-timeout/
 * <p>
 * 服务网关Zuul高级篇: http://www.ityouknow.com/springcloud/2018/01/20/spring-cloud-zuul.html
 * <p>
 * Spring Cloud Zuul的fallback优化: http://blog.didispace.com/spring-cloud-zuul-fallback-improve/
 * <p>
 * Zuul工作原理：https://blog.wangqi.love/articles/Spring-Cloud/Zuul%E6%8E%A2%E7%A9%B6(%E4%BA%8C)%E2%80%94%E2%80%94Zuul%E7%9A%84%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86.html
 * <p>
 * 使用 Zuul 实现 API Gateway 的路由和过滤：https://www.jianshu.com/p/e0434a421c03
 *
 * @see org.springframework.cloud.netflix.zuul.filters.route.support.AbstractRibbonCommand
 * @see org.springframework.cloud.netflix.zuul.ZuulServerAutoConfiguration
 * @see com.netflix.client.config.DefaultClientConfigImpl
 * @see com.netflix.hystrix.HystrixCommand
 * @see com.netflix.zuul.http.ZuulServlet
 */
@SpringCloudApplication
@EnableFeignClients
public class AggregatorApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(AggregatorApplication.class, args);
    }
}
