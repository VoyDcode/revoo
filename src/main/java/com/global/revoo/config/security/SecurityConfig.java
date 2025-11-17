package com.global.revoo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // pra facilitar testes de API
            .authorizeHttpRequests(auth -> auth
                // libera Swagger e OpenAPI
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**"
                ).permitAll()
                // o resto precisa de login
                .anyRequest().authenticated()
            )
            // form login padrão do Spring
            .formLogin(Customizer.withDefaults())
            
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
