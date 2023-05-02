package com.basejava.cart.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Autowired
    private AuthenticationProvider provider;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception{
        return http.csrf().disable()
                .authorizeHttpRequests(
                        auth ->{
                            auth.requestMatchers(HttpMethod.POST, "/api/users").permitAll();
                            auth.requestMatchers(HttpMethod.POST, "/api/auth").permitAll();
                            auth.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();
                            auth.requestMatchers(HttpMethod.POST, "/api/users/**").permitAll();
                            /*auth.requestMatchers(HttpMethod.POST, "/api/cart/**").permitAll();
                            auth.requestMatchers(HttpMethod.GET, "/api/cart/**").permitAll();*/

                            auth.anyRequest().permitAll();
                        }
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(provider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
