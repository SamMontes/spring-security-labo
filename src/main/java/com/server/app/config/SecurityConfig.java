package com.server.app.config;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.server.app.config.JwtAuthenticationFilter;
import com.server.app.config.DynamicAuthorizationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final DynamicAuthorizationFilter dynamicAuthorizationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, DynamicAuthorizationFilter dynamicAuthorizationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.dynamicAuthorizationFilter = dynamicAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                //.addFilterAfter((Filter) dynamicAuthorizationFilter, JwtAuthenticationFilter.class);

        return http.build();
    }
}
