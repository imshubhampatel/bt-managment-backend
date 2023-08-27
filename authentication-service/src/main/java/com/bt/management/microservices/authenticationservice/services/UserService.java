package com.bt.management.microservices.authenticationservice.services;

import com.bt.management.microservices.authenticationservice.dto.AuthenticationResponse;
import com.bt.management.microservices.authenticationservice.dto.UserDto;
import com.bt.management.microservices.authenticationservice.exceptions.ResourceNotFoundException;
import com.bt.management.microservices.authenticationservice.helpers.JwtHelper;
import com.bt.management.microservices.authenticationservice.models.Institution;
import com.bt.management.microservices.authenticationservice.models.User;
import com.bt.management.microservices.authenticationservice.repositories.InstitutionRepository;
import com.bt.management.microservices.authenticationservice.repositories.UserRepository;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  InstitutionRepository institutionRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  JwtHelper jwtHelper;

  public AuthenticationResponse registerUser(UserDto userReq) {
    User user = convertToUser(userReq);
    userRepository.save(user);
    var jwtToken = jwtHelper.generateToken(user);
    return AuthenticationResponse
      .builder()
      .token(jwtToken)
      .success(true)
      .status(HttpStatus.SC_CREATED)
      .message("User Registered Successfully")
      .data(user)
      .build();
  }

  private User convertToUser(UserDto userReq) {
    User user = new User();
    user.setFirstName(userReq.getFirstName());
    user.setLastName(userReq.getLastName());
    user.setDateOfBirth(userReq.getDateOfBirth());
    user.setAge(userReq.getAge());
    user.setDateOfJoining(userReq.getDateOfJoining());
    user.setEmail(userReq.getEmail());
    user.setPhoneNumber(userReq.getPhoneNumber());
    user.setEnrollmentNumber(userReq.getEnrollmentNumber());
    user.setUsername(userReq.getUsername());
    user.setPassword(passwordEncoder.encode(userReq.getPassword()));
    user.setSerialNumber(userReq.getSerialNumber());
    user.setBranch(userReq.getBranch());
    user.setCourse(userReq.getCourse());
    user.setSemester(userReq.getSemester());
    user.setYear(userReq.getYear());
    user.setRole(userReq.getRole());
    user.setAbout(userReq.getAbout());
    user.setAddress(userReq.getAddress());
    user.setGender(userReq.getGender());

    if (userReq.getInstitution() != null) {
      Institution institution = institutionRepository
        .findById(userReq.getInstitution())
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Institution",
            "Id",
            userReq.getInstitution().toString()
          )
        );

      user.setInstitution(institution);
    }

    return user;
  }
}
