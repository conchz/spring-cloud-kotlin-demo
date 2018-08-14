package com.github.lavenderx.demo.client;

import com.github.lavenderx.demo.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${client.servicea}", path = "user")
public interface UseraFeignClient {

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    User queryUserInfo(@PathVariable("id") Long id);
}
