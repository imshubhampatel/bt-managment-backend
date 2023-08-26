package com.bt.management.microservices.authenticationservice.exceptions;

import lombok.Data;

@Data
public class ResourceAlreadyExistsException extends RuntimeException {

  String resourceName;
  String fieldName;
  String fieldValue;

  public ResourceAlreadyExistsException(
    String resourceName,
    String fieldName,
    String fieldValue
  ) {
    super(
      String.format(
        "%s is already registered with %s : %s",
        resourceName,
        fieldName,
        fieldValue
      )
    );
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }
}
