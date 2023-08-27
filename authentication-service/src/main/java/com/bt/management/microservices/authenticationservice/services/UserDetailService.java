// package com.bt.management.microservices.authenticationservice.services;

// import com.bt.management.microservices.authenticationservice.models.User;
// import com.bt.management.microservices.authenticationservice.repositories.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class UserDetailService implements UserDetailsService {

//   @Autowired
//   UserRepository userRepository;

//   @Override
//   public User loadUserByUsername(String userEmail)
//     throws UsernameNotFoundException {
//     return userRepository
//       .findByEmail(userEmail)
//       .orElseThrow(() -> new UsernameNotFoundException("userEmail not found"));
//   }
// }
