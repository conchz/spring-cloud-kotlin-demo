package com.github.lavenderx.demo.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTAuthentication extends AbstractAuthenticationToken {

    private final UserDetails principal;
    private final String jsonWebToken;

    public JWTAuthentication(UserDetails principal, String jsonWebToken) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getJsonWebToken() {
        return jsonWebToken;
    }
}