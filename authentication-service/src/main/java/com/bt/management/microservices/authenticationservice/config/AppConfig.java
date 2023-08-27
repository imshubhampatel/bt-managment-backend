package com.bt.management.microservices.authenticationservice.config;

import com.bt.management.microservices.authenticationservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@AllArgsConstructor
public class AppConfig {

  @Autowired
  UserRepository userRepository;

  @Bean
  public UserDetailsService userDetailService() {
    return username ->
      userRepository
        .findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("username not found"));
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration builder
  ) throws Exception {
    return builder.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }
}
