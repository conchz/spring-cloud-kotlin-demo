package com.github.lavenderx.demo.controller;

import com.github.lavenderx.demo.client.ServiceaFeignClient;
import com.github.lavenderx.demo.model.User;
import com.github.lavenderx.demo.protocol.response.BaseResponse;
import com.github.lavenderx.demo.service.AggregationService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import java.util.HashMap;

@RestController
public class AggregationController extends BaseController {

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
    public DeferredResult<BaseResponse> aggregate(@PathVariable Long id) {
        Observable<BaseResponse> result = aggregateObservable(id);
        return toDeferredResult(result);
    }

    public Observable<BaseResponse> aggregateObservable(Long id) {
        // 合并两个或者多个Observables发射出的数据项，根据指定的函数变换它们
        return Observable.zip(
                this.aggregationService.getUseraById(id),
                this.aggregationService.getUserbById(id),
                (usera, userb) -> {
                    BaseResponse<HashMap<String, User>> response = new BaseResponse<>();
                    HashMap<String, User> map = Maps.newHashMap();
                    map.put("usera", usera);
                    map.put("userb", userb);

                    response.setCode(1);
                    response.setMessage("OK");
                    response.setData(map);

                    return response;
                }
        );
    }

}
