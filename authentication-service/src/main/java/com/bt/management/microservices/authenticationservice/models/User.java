package com.bt.management.microservices.authenticationservice.models;

import com.bt.management.microservices.authenticationservice.dto.BranchEnum;
import com.bt.management.microservices.authenticationservice.dto.QualificationEnum;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
// public class User implements UserDetails {
public class User {

  @Id
  private String id;

  @Field
  private String firstName;

  @Field
  private String lastName;

  @Field
  private String email;

  @Field
  private String password;

  @Indexed(unique = true)
  private String username;

  @Field
  private String age;

  @Field
  private String enrollmentNumber;

  @Field
  private String serialNumber;

  @Field
  private BranchEnum branch = BranchEnum.NOT_SELECTED;

  @Field
  private QualificationEnum qualification = QualificationEnum.NOT_SELECTED;

  @Field
  private Date dateOfBirth;

  @Field
  private Date dateOfJoining;

  @Field
  private String about;

  @DocumentReference
  @Field
  private Institution institution;
  // @Override
  // public Collection<? extends GrantedAuthority> getAuthorities() {
  //   return Collections.EMPTY_LIST;
  // }

  // @Override
  // public boolean isAccountNonExpired() {
  //   return true;
  // }

  // @Override
  // public boolean isAccountNonLocked() {
  //   return true;
  // }

  // @Override
  // public boolean isCredentialsNonExpired() {
  //   return true;
  // }

  // @Override
  // public boolean isEnabled() {
  //   return true;
  // }
}
