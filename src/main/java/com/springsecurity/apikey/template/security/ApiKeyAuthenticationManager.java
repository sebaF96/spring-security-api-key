package com.springsecurity.apikey.template.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiKeyAuthenticationManager implements AuthenticationManager {
    private final String apiKey = System.getenv("API_KEY");

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        if (principal.isBlank()) {
            throw new BadCredentialsException("The API key is not present");
        } else if (!this.apiKey.equals(principal)) {
            throw new BadCredentialsException("The API key is invalid");
        }
        authentication.setAuthenticated(true);
        return authentication;
    }
}

