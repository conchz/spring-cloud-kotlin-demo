package com.github.lavenderx.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Slf4j
public class BeforeZuulServletFilter implements Filter {

    public static final String REQUEST_ID = "X-Request-ID";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long requestId = Instant.now().toEpochMilli();
        log.info("X-Request-ID: {}", requestId);
        HttpServletResponse httpServletResponse = ((HttpServletResponse) response);
        if (StringUtils.isEmpty(httpServletResponse.getHeader(REQUEST_ID))) {
            httpServletResponse.addHeader(REQUEST_ID, String.valueOf(requestId));
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        ;
    }
}
