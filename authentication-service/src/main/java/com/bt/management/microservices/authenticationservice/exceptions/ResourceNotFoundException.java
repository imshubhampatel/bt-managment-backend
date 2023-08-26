package com.bt.management.microservices.authenticationservice.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

  String resourceName;
  String filedName;
  String fieldValue;

  public ResourceNotFoundException(
    String resourceName,
    String fieldName,
    String fieldValue
  ) {
    super(
      String.format(
        "%s is not registered with %s : %s",
        resourceName,
        fieldName,
        fieldValue
      )
    );
    this.fieldValue = fieldValue;
    this.filedName = fieldName;
    this.resourceName = resourceName;
  }
}
