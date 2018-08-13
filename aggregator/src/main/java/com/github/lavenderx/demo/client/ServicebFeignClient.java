package com.github.lavenderx.demo.client;

import com.github.lavenderx.demo.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "serviceb-v1")
public interface ServicebFeignClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User queryUserInfo(@PathVariable("id") Long id);
}
