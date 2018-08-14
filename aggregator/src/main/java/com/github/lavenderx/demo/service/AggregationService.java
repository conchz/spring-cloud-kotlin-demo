package com.github.lavenderx.demo.service;

import com.github.lavenderx.demo.client.ServiceaFeignClient;
import com.github.lavenderx.demo.client.ServicebFeignClient;
import com.github.lavenderx.demo.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

@Slf4j
@Service
public class AggregationService {

    private final RestTemplate restTemplate;
    private final ServiceaFeignClient serviceaFeignClient;
    private final ServicebFeignClient servicebFeignClient;

    @Autowired
    public AggregationService(RestTemplate restTemplate,
                              ServiceaFeignClient serviceaFeignClient,
                              ServicebFeignClient servicebFeignClient) {
        this.restTemplate = restTemplate;
        this.serviceaFeignClient = serviceaFeignClient;
        this.servicebFeignClient = servicebFeignClient;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUseraById(Long id) {
        // 创建一个被观察者
        return Observable.create(observer -> {
            User usera = serviceaFeignClient.queryUserInfo(id);
//            User usera = restTemplate.getForObject("http://servicea-v1/user/{1}", User.class, id);
            observer.onNext(usera);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserbById(Long id) {
        return Observable.create(observer -> {
            User userb = servicebFeignClient.queryUserInfo(id);
            observer.onNext(userb);
            observer.onCompleted();
        });
    }

    public User fallback(Long id) {
        User user = new User();
        user.setId(-1L);
        return user;
    }
}