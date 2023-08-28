package com.bt.management.microservices.authenticationservice.controllers;

import com.bt.management.microservices.authenticationservice.dto.Authentication.UserLoginRequest;
import com.bt.management.microservices.authenticationservice.dto.AuthenticationResponse;
import com.bt.management.microservices.authenticationservice.dto.UserDto;
import com.bt.management.microservices.authenticationservice.exceptions.ApiResponse;
import com.bt.management.microservices.authenticationservice.models.User;
import com.bt.management.microservices.authenticationservice.services.UserService;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.util.Map;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication-service/auth")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/register-user")
  public ResponseEntity<AuthenticationResponse> registerUser(
    @RequestBody UserDto entity
  ) {
    return ResponseEntity.ok(userService.registerUser(entity));
  }

  @PostMapping("/login")
  // public ResponseEntity<AuthenticationResponse> loginUser(
  public ResponseEntity<AuthenticationResponse> loginUser(
    @RequestBody UserLoginRequest userRequest
  ) {
    System.out.println(userRequest);
    Map<String, Object> authMap = userService.authenticateUser(userRequest);
    AuthenticationResponse apiResponse = new AuthenticationResponse();
    apiResponse.setMessage("User logged in Successfully");
    apiResponse.setStatus(HttpStatus.SC_OK);
    apiResponse.setSuccess(true);
    apiResponse.setData(authMap.get("user"));
    apiResponse.setToken(authMap.get("token").toString());
    return ResponseEntity.status(HttpStatus.SC_OK).body(apiResponse);
  }
}
