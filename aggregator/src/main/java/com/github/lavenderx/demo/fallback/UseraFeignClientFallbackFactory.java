package com.github.lavenderx.demo.fallback;

import com.github.lavenderx.demo.client.UseraFeignClient;
import com.github.lavenderx.demo.model.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UseraFeignClientFallbackFactory implements FallbackFactory<UseraFeignClient> {

    @Override
    public UseraFeignClient create(Throwable t) {
        return new UseraFeignClient() {
            @Override
            public User queryUserInfo(Long id) {
                log.warn("fallback cause: {}", t.getMessage());
                User user = new User();
                user.setId(-2L);
                return user;
            }
        };
    }
}
