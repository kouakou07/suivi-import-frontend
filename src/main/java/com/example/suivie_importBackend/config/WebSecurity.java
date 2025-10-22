package com.example.suivie_importBackend.config;

import java.util.Arrays;

import com.example.suivie_importBackend.service.AuthEntryPointService;
import com.example.suivie_importBackend.service.AuthFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurity {
    
    @Autowired
    AuthEntryPointService authEntryPointService;

    @Autowired
    AuthFilterService authFilterService;
   
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cor -> {
            //cor.disable();
        })
        .csrf(csrfProtect -> {
            csrfProtect.disable();
        });

        http.formLogin(form -> {
            form.disable();
        });

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/test/**",
                                    "/api/login",
                                    "/api/login/password",
                                    "/api/article/info/{articleId}",
                                    "/api/fournisseur/liste-fournisseur",
                                    "/api/fournisseur/liste/{page}"
                                    //"/v3/api-docs"
            )
            .permitAll()
            .anyRequest()
            .authenticated();
        });

        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(authEntryPointService);
        });

        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.addFilterBefore(authFilterService, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
