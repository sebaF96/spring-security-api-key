package com.springsecurity.apikey.template.security;

import com.springsecurity.apikey.template.security.provider.ApiKeyProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor
public class ApiKeyAuthenticationManager implements AuthenticationManager {
    private ApiKeyProvider apiKeyProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        if (principal.isBlank()) {
            throw new BadCredentialsException("The API key is not present");
        } else if (!this.apiKeyProvider.getApiKey().equals(principal)) {
            throw new BadCredentialsException("The API key is invalid");
        }
        authentication.setAuthenticated(true);
        return authentication;
    }
}

