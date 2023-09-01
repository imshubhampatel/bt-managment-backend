package com.bt.management.microservices.authenticationservice.services;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelperService {

  public ResponseEntity<Object> generateResponse(
    String message,
    HttpStatus status,
    Object data
  ) {
    Map<String, Object> apiResponse = new HashMap<String, Object>();
    apiResponse.put("message", message);
    apiResponse.put("status", status.value());
    apiResponse.put("success", true);
    if (data != null) {
      apiResponse.put("data", data);
    }
    return new ResponseEntity<Object>(
      apiResponse,
      HttpStatus.valueOf(status.value())
    );
  }

  public void getResponse(
    HttpServletResponse response,
    HttpStatus status,
    String message
  ) throws IOException {
    String responseJson =
      "{\"status\":\"" + status.value() + "\",\"message\":\"" + message + "\"}";

    response.setStatus(status.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    PrintWriter writer = null;
    try {
      writer = response.getWriter();
      writer.write(responseJson);
      writer.flush();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
  }
}
