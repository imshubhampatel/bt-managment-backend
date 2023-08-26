package com.bt.management.microservices.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class InstitutionRequest {

  private String name;

  private String code;

  private String address;

  private String state;

  private String city;

  private String zipCode;

  private String openingTime;

  private String closingTime;

  private String contactNumber1;

  private String contactNumber2;

  private StatusEnum status = StatusEnum.OPEN;
}
