package com.github.lavenderx.demo.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
@EnableAspectJAutoProxy
@EnableAsync
public class WebConfig extends WebMvcConfigurerAdapter {
}
