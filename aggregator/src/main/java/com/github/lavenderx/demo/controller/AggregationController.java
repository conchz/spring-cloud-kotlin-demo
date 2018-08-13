package com.github.lavenderx.demo.controller;

import com.github.lavenderx.demo.client.ServiceaFeignClient;
import com.github.lavenderx.demo.model.User;
import com.github.lavenderx.demo.service.AggregationService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;

@Slf4j
@RestController
public class AggregationController {

    private final ServiceaFeignClient serviceaFeignClient;
    private final AggregationService aggregationService;

    @Autowired
    public AggregationController(ServiceaFeignClient serviceaFeignClient,
                                 AggregationService aggregationService) {
        this.serviceaFeignClient = serviceaFeignClient;
        this.aggregationService = aggregationService;
    }

    @GetMapping("/api/v1/servicea/user/1")
    public User testServicea(@PathVariable("id") Long id) {
        return serviceaFeignClient.queryUserInfo(id);
    }

    @GetMapping("/api/v1/aggs/aggregate/{id}")
    public DeferredResult<HashMap<String, User>> aggregate(@PathVariable Long id) {
        Observable<HashMap<String, User>> result = this.aggregateObservable(id);
        return this.toDeferredResult(result);
    }

    public Observable<HashMap<String, User>> aggregateObservable(Long id) {
        // 合并两个或者多个Observables发射出的数据项，根据指定的函数变换它们
        return Observable.zip(
                this.aggregationService.getUseraById(id),
                this.aggregationService.getUserbById(id),
                (user, movieUser) -> {
                    HashMap<String, User> map = Maps.newHashMap();
                    map.put("usera", user);
                    map.put("userb", movieUser);
                    return map;
                }
        );
    }

    public DeferredResult<HashMap<String, User>> toDeferredResult(Observable<HashMap<String, User>> details) {
        DeferredResult<HashMap<String, User>> result = new DeferredResult<>();
        // 订阅
        details.subscribe(new Observer<HashMap<String, User>>() {
            @Override
            public void onCompleted() {
                log.info("完成...");
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("发生错误...", throwable);
            }

            @Override
            public void onNext(HashMap<String, User> movieDetails) {
                result.setResult(movieDetails);
            }
        });
        return result;
    }

}
