package com.bt.management.microservices.authenticationservice.config;

import com.bt.management.microservices.authenticationservice.helpers.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  JwtHelper jwtHelper;

  @Autowired
  UserDetailsService userDetailsService;

  private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwtToken;
    final String userEmail;
    if (authHeader == null || !authHeader.startsWith("Bearer")) {
      filterChain.doFilter(request, response);
      return;
    }

    jwtToken = authHeader.substring(7);
    logger.info(" Header :  {}", authHeader);
    userEmail = jwtHelper.getUsernameFromToken(jwtToken);

    if (
      userEmail != null &&
      SecurityContextHolder.getContext().getAuthentication() == null
    ) {
      UserDetails userDetails =
        this.userDetailsService.loadUserByUsername(userEmail);
      if (jwtHelper.validateToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          userEmail,
          null,
          userDetails.getAuthorities()
        );

        authToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}