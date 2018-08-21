package com.github.lavenderx.demo.servlet;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Slf4j
@Component
public class RequestIdInterceptor extends HandlerInterceptorAdapter {

    public static final String REQUEST_ID = "req_id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.set(REQUEST_ID, Instant.now().toEpochMilli());
        log.info("REQUEST_ID: {}", requestContext.get(REQUEST_ID).toString());

        return true;
    }
}
