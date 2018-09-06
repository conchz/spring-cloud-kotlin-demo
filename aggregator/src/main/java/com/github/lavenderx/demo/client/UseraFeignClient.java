package com.github.lavenderx.demo.client;

import com.github.lavenderx.demo.fallback.UseraFeignClientFallbackFactory;
import com.github.lavenderx.demo.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * https://blog.csdn.net/c764193441/article/details/79198130
 */
@FeignClient(name = "${client.servicea}", path = "user", fallbackFactory = UseraFeignClientFallbackFactory.class)
public interface UseraFeignClient {

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    User queryUserInfo(@PathVariable("id") Long id);
}
