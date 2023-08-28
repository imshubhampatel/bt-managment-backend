package com.bt.management.microservices.authenticationservice.exceptions;

import com.bt.management.microservices.authenticationservice.dto.AuthenticationResponse;
import io.jsonwebtoken.ExpiredJwtException;
import java.nio.file.AccessDeniedException;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(
    ResourceNotFoundException ex
  ) {
    String message = ex.getMessage();
    ApiResponse apiResponse = new ApiResponse(
      message,
      false,
      HttpStatus.SC_NOT_FOUND
    );

    return new ResponseEntity<ApiResponse>(
      apiResponse,
      HttpStatusCode.valueOf(HttpStatus.SC_NOT_FOUND)
    );
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ApiResponse> resourceAlreadyExistsExceptionHandler(
    ResourceAlreadyExistsException ex
  ) {
    String message = ex.getMessage();
    ApiResponse apiResponse = new ApiResponse(
      message,
      false,
      HttpStatus.SC_CONFLICT
    );
    return new ResponseEntity<ApiResponse>(
      apiResponse,
      HttpStatusCode.valueOf(HttpStatus.SC_CONFLICT)
    );
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiResponse> badCredentialsException(
    BadCredentialsException ex
  ) {
    String message = ex.getMessage();
    ApiResponse apiResponse = new ApiResponse(
      message,
      false,
      HttpStatus.SC_UNAUTHORIZED
    );

    return new ResponseEntity<ApiResponse>(
      apiResponse,
      HttpStatusCode.valueOf(HttpStatus.SC_UNAUTHORIZED)
    );
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiResponse> accessDeniedException(
    ExpiredJwtException ex
  ) {
    String message = ex.getMessage();
    ApiResponse apiResponse = new ApiResponse(
      message,
      false,
      HttpStatus.SC_UNAUTHORIZED
    );

    return new ResponseEntity<ApiResponse>(
      apiResponse,
      HttpStatusCode.valueOf(HttpStatus.SC_UNAUTHORIZED)
    );
  }
}
