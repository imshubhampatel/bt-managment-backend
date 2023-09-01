package com.bt.management.microservices.authenticationservice.config;

import com.bt.management.microservices.authenticationservice.exceptions.ExpiredJwtTokenException;
import com.bt.management.microservices.authenticationservice.services.HelperService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Autowired
  private HelperService helperService;

  @Override
  public void commence(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException authException
  ) throws IOException, ServletException {
    System.out.println(
      "Unauthorized -------------------" +
      authException.getClass().getSimpleName()
    );
    System.out.println("Unauthorized " + authException.getMessage());
    helperService.getResponse(
      response,
      HttpStatus.UNAUTHORIZED,
      authException.getMessage()
    );
  }
}
