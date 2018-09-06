package com.github.lavenderx.demo.service;

import com.github.lavenderx.demo.client.UseraFeignClient;
import com.github.lavenderx.demo.client.UserbFeignClient;
import com.github.lavenderx.demo.model.User;
import com.github.lavenderx.demo.protocol.response.BaseResponse;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import java.util.HashMap;
import java.util.function.Function;

/**
 * User usera = restTemplate.getForObject("http://servicea-v1/user/{1}", User.class, id);
 */
@Slf4j
@Service
public class AggregationService extends BaseAggsService {

    private final RestTemplate restTemplate;
    private final UseraFeignClient useraFeignClient;
    private final UserbFeignClient userbFeignClient;

    @Autowired
    public AggregationService(RestTemplate restTemplate,
                              UseraFeignClient useraFeignClient,
                              UserbFeignClient userbFeignClient) {
        this.restTemplate = restTemplate;
        this.useraFeignClient = useraFeignClient;
        this.userbFeignClient = userbFeignClient;
    }

    @HystrixCommand(fallbackMethod = "getUserInfoFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
    })
    public DeferredResult<BaseResponse> getUserInfo(Long id) {
        return toDeferredResult(createUserInfoObservable(id));
    }

    public <T, R> Observable<R> handle(T arg, Function<T, R> function) {
        // 创建一个被观察者
        return Observable.create(observer -> {
            observer.onNext(function.apply(arg));
            observer.onCompleted();
        });
    }

    public Observable<BaseResponse> createUserInfoObservable(Long id) {
        // 合并两个或者多个Observables发射出的数据项，根据指定的函数变换它们
        return Observable.zip(
                handle(id, useraFeignClient::queryUserInfo),
                handle(id, userbFeignClient::queryUserInfo),
                (usera, userb) -> {
                    // 以下为业务数据处理逻辑

                    BaseResponse<HashMap<String, User>> response = new BaseResponse<>();
                    HashMap<String, User> map = Maps.newHashMap();
                    map.put("usera", usera);
                    map.put("userb", userb);

                    response.setCode(0);
                    response.setMessage("OK");
                    response.setData(map);

                    return response;
                }
        );
    }

    public DeferredResult<BaseResponse> getUserInfoFallback(Long id) {
        DeferredResult<BaseResponse> deferredResult = new DeferredResult<>();
        deferredResult.setResult(new BaseResponse(1, "ERROR"));
        return deferredResult;
    }
}