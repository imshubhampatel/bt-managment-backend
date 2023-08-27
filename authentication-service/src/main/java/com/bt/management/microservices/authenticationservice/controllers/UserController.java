package com.bt.management.microservices.authenticationservice.controllers;

import com.bt.management.microservices.authenticationservice.dto.AuthenticationResponse;
import com.bt.management.microservices.authenticationservice.dto.UserDto;
import com.bt.management.microservices.authenticationservice.models.User;
import com.bt.management.microservices.authenticationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/register-user")
  public ResponseEntity<AuthenticationResponse> registerUser(
    @RequestBody UserDto entity
  ) {
    return ResponseEntity.ok(userService.registerUser(entity));
  }
}
