package com.github.lavenderx.demo.service;

import com.github.lavenderx.demo.protocol.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

public abstract class BaseAggsService {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public DeferredResult<BaseResponse> toDeferredResult(Observable<BaseResponse> obr) {
        final DeferredResult<BaseResponse> result = new DeferredResult<>();
        obr.subscribe(new Observer<BaseResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                log.error("Exception on handling DeferredResult", e);
                // TODO Error handling
            }

            @Override
            public void onNext(BaseResponse br) {
                result.setResult(br);
            }
        });

        return result;
    }
}
