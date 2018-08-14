package com.github.lavenderx.demo.controller;

import com.github.lavenderx.demo.protocol.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

public abstract class BaseController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected DeferredResult<BaseResponse> toDeferredResult(Observable<BaseResponse> observableResponse) {
        final DeferredResult<BaseResponse> result = new DeferredResult<>();
        observableResponse.subscribe(new Observer<BaseResponse>() {
            @Override
            public void onCompleted() {
                log.info("DeferredResult finished");
            }

            @Override
            public void onError(Throwable e) {
                log.error("Exception on handling DeferredResult", e);
            }

            @Override
            public void onNext(BaseResponse o) {
                result.setResult(o);
            }
        });

        return result;
    }
}
