package com.github.lavenderx.demo.config;

import com.github.lavenderx.demo.servlet.RequestIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
@EnableAspectJAutoProxy
@EnableAsync
public class WebConfig extends WebMvcConfigurerAdapter {

    private final RequestIdInterceptor requestIdInterceptor;

    @Autowired
    public WebConfig(RequestIdInterceptor requestIdInterceptor) {
        this.requestIdInterceptor = requestIdInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(requestIdInterceptor);
        ir.addPathPatterns("/**");

    }
}
