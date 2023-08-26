package com.bt.management.microservices.authenticationservice.controllers;

import com.bt.management.microservices.authenticationservice.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @PostMapping("/register-user")
  public User postMethodName(@RequestBody User entity) {
    //TODO: process POST request

    return entity;
  }
}
