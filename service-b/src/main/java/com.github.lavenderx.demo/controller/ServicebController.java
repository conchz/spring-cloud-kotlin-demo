package com.github.lavenderx.demo.controller;

import com.github.lavenderx.demo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ServicebController {

    @GetMapping("/user/{id}")
    public User getUserInfo(@PathVariable("id") Long id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);

        User user = new User();
        user.setId(id);
        user.setUsername("李四");
        user.setEmail("lisi@gmail.com");
        user.setAge(30);

        return user;
    }
}
