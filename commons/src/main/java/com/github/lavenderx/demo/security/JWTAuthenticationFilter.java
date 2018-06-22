package com.github.lavenderx.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    public JWTAuthenticationFilter() {
        // Don't throw exceptions if the header is missing
        this.setExceptionIfHeaderMissing(false);

        // This is the request header it will look for
        this.setPrincipalRequestHeader("X-Access-Token");
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

}