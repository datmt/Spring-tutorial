package com.datmt.security.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.datmt.security.jwt.service.CustomUserDetailsService;
import com.datmt.security.jwt.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
  
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .addFilterBefore(
            new JwtRequestFilter(customUserDetailsService, jwtUtil),
            UsernamePasswordAuthenticationFilter.class
        )
        .authorizeHttpRequests((requests) ->  requests
           .requestMatchers("/admin/**").hasRole("ADMIN")
           .requestMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER")
           .requestMatchers("/", "/api/public/**").permitAll()
           .anyRequest().authenticated())
           .csrf(AbstractHttpConfigurer::disable)
           .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
           .logout(LogoutConfigurer::permitAll);
       
           return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationProviderBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
