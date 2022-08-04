package com.springsecurity.apikey.template;

import com.springsecurity.apikey.template.security.SecurityConfiguration;
import com.springsecurity.apikey.template.security.provider.ApiKeyProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@Import(SecurityConfiguration.class)
public class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ApiKeyProvider mockSystemApiKeyProvider;

    @Test
    void testNoApiKeyForbidden() throws Exception {
        mockMvc.perform(get("/test")).andExpect(status().isForbidden());
    }

    @Test
    void testInvalidApiKeyForbidden() throws Exception {
        Mockito.when(mockSystemApiKeyProvider.getApiKey()).thenReturn("valid");
        mockMvc.perform(get("/test").header("X-API-KEY", "invalid")).andExpect(status().isForbidden());
    }

    @Test
    void testValidApiKeyOk() throws Exception {
        Mockito.when(mockSystemApiKeyProvider.getApiKey()).thenReturn("valid");
        mockMvc.perform(get("/test").header("X-API-KEY", "valid")).andExpect(status().isOk());
    }
}
