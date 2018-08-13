package com.github.lavenderx.demo.controller;

import com.github.lavenderx.demo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceaController {

    @GetMapping("/user/{id}")
    public User getUserInfo(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("张三");
        user.setEmail("zhangsan@gmail.com");
        user.setAge(36);

        return user;
    }
}
