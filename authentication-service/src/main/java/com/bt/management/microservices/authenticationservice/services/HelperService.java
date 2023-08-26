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
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", message);
    map.put("status", status);
    if (data != null) {
      map.put("data", data);
    }
    return new ResponseEntity<Object>(map, HttpStatusCode.valueOf(status));
  }
}
