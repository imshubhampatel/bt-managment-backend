package com.bt.management.microservices.authenticationservice.services;

import com.bt.management.microservices.authenticationservice.models.User;
import com.bt.management.microservices.authenticationservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

  @Autowired
  UserRepository userRepository;

  public User registerUser(User userReq) {
    User user = convertToUser(userReq);
    return user;
  }

  private User convertToUser(User userReq) {
    User user = new User();
    user.setFirstName(userReq.getFirstName());
    user.setLastName(userReq.getLastName());
    user.setEmail(userReq.getEmail());
    user.setPassword(userReq.getPassword());
    user.setPhoneNumber(userReq.getPhoneNumber());
    user.setEnrollmentNumber(userReq.getEnrollmentNumber());
    user.setInstitution(userReq.getInstitution());

    return user;
  }
}
