package com.springsecurity.apikey.template.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ApiKeyAuthenticationFilter filter = new ApiKeyAuthenticationFilter();
        AuthenticationManager authManager = this.authenticationManagerBean();
        filter.setAuthenticationManager(authManager);
        http
                .csrf().disable()
                .antMatcher("/**")
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilter(filter).authorizeHttpRequests().anyRequest().authenticated();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() {
        return new ApiKeyAuthenticationManager();
    }
}
