package com.github.lavenderx.demo.config;

import com.github.lavenderx.demo.filter.MySimpleFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@EnableZuulProxy
public class ZuulConfig {

    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }

    // pre filters
    @Bean
    public MySimpleFilter mySimpleFilter() {
        return new MySimpleFilter();
    }
}
