package com.springsecurity.apikey.template.security.provider;

import org.springframework.stereotype.Component;

@Component
public class SystemApiKeyProvider implements ApiKeyProvider {
    @Override
    public String getApiKey() {
        return System.getenv("API_KEY");
    }
}
