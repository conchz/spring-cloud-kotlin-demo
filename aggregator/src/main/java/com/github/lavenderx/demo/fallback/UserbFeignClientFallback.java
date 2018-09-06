package com.github.lavenderx.demo.fallback;

import com.github.lavenderx.demo.client.UserbFeignClient;
import com.github.lavenderx.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserbFeignClientFallback implements UserbFeignClient {

    @Override
    public User queryUserInfo(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setUsername("unknown");
        return user;
    }
}
