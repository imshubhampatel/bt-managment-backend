package com.bt.management.microservices.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private Boolean success;
  private Object data;
  private String token;
  private String message;
  private Integer status;
}
