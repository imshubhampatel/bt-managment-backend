package com.bt.management.microservices.authenticationservice.exceptions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ExpiredJwtTokenException extends RuntimeException {

  Header messageHeader;
  Claims messageClaim;
  String message;

  public ExpiredJwtTokenException(String message) {
    super(message);
    this.message = message;
  }
}
