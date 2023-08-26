package com.bt.management.microservices.authenticationservice.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelperService {

  public ResponseEntity<Object> generateResponse(
    String message,
    int status,
    Object data
  ) {
    Map<String, Object> apiResponse = new HashMap<String, Object>();
    apiResponse.put("message", message);
    apiResponse.put("status", status);
    apiResponse.put("success", true);
    if (data != null) {
      apiResponse.put("data", data);
    }
    return new ResponseEntity<Object>(
      apiResponse,
      HttpStatusCode.valueOf(status)
    );
  }
}
