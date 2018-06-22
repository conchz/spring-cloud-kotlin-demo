package com.github.lavenderx.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public abstract class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationProvider authenticationProvider;

    @Autowired
    private JWTAuthenticationFilter jsonWebTokenFilter;
}
