package com.bt.management.microservices.authenticationservice.config;

import com.mongodb.client.model.geojson.Point;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  @Autowired
  JwtAuthenticationFilter jwtAuthFilter;

  @Autowired
  AuthenticationProvider authenticationProvider;

  @Autowired
  JwtAuthenticationEntryPoint point;

  @Bean
  public SecurityFilterChain securityFilterChain(
    HttpSecurity http,
    AuthenticationEntryPoint point
  ) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .cors(cors -> cors.disable())
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers("/api/v1/authentication-service/auth/**")
          .permitAll()
          .anyRequest()
          .authenticated()
      )
      .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(
        jwtAuthFilter,
        UsernamePasswordAuthenticationFilter.class
      );

    return http.build();
  }
}
