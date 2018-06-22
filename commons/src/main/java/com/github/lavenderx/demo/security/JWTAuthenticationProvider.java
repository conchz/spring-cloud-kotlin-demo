package com.github.lavenderx.demo.security;

import com.github.lavenderx.demo.util.JWTUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticatedUser = null;
        // Only process the PreAuthenticatedAuthenticationToken
        if (authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                && authentication.getPrincipal() != null) {
            String tokenHeader = (String) authentication.getPrincipal();
            UserDetails userDetails = parseToken(tokenHeader);
            if (userDetails != null) {
                authenticatedUser = new JWTAuthentication(userDetails, tokenHeader);
            }
        } else {
            // It is already a JsonWebTokenAuthentication
            authenticatedUser = authentication;
        }
        return authenticatedUser;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                || authentication.isAssignableFrom(JWTAuthentication.class);
    }

    private UserDetails parseToken(String tokenHeader) {
        UserDetails principal = null;
        Long userId = JWTUtils.getUserId(tokenHeader);
        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");

        if (userId != null) {
            // userId介入Spring Security
            principal = new User(userId.toString(), "", authorityList);
        }

        return principal;
    }
}
