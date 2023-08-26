package com.bt.management.microservices.authenticationservice.exceptions;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(
    ResourceNotFoundException ex
  ) {
    System.out.println("ex" + ex);
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
}
