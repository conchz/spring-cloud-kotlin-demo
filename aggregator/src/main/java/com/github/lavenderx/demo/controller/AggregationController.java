package com.github.lavenderx.demo.controller;

import com.github.lavenderx.demo.protocol.response.BaseResponse;
import com.github.lavenderx.demo.service.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AggregationController {

    private final AggregationService aggregationService;

    @Autowired
    public AggregationController(AggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    @GetMapping("/api/v1/aggs/user/{id}")
    public DeferredResult<BaseResponse> aggregate(@PathVariable Long id) {
        return aggregationService.getUserInfo(id);
    }
}
