package com.bt.management.microservices.authenticationservice.models;

import com.bt.management.microservices.authenticationservice.dto.Base;
import com.bt.management.microservices.authenticationservice.dto.BranchEnum;
import com.bt.management.microservices.authenticationservice.dto.CoursesEnum;
import com.bt.management.microservices.authenticationservice.dto.GenderEnum;
import com.bt.management.microservices.authenticationservice.dto.SemesterEnum;
import com.bt.management.microservices.authenticationservice.dto.UserRoleEnum;
import com.bt.management.microservices.authenticationservice.dto.YearEnum;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
// public class User implements UserDetails {
public class User extends Base implements UserDetails {

  @Id
  private String id;

  @Field
  private String firstName;

  @Field
  private String lastName;

  @Field
  private String dateOfBirth;

  @Field
  private Integer age;

  @Field
  private String dateOfJoining;

  @Indexed(unique = true)
  @Field
  private String email;

  @Indexed(unique = true)
  @Field
  private String phoneNumber;

  @Indexed(unique = true)
  @Field
  private String enrollmentNumber;

  @Indexed(unique = true)
  private String username;

  @Field
  private String password;

  @Field
  private String serialNumber;

  @Field
  private BranchEnum branch = BranchEnum.NOT_SELECTED;

  @Field
  private CoursesEnum course = CoursesEnum.NOT_SELECTED;

  @Field
  private SemesterEnum semester = SemesterEnum.NOT_SELECTED;

  @Field
  private YearEnum year = YearEnum.NOT_SELECTED;

  @Field
  private UserRoleEnum role = UserRoleEnum.NOT_SELECTED;

  @Field
  private String about;

  @Field
  private String address;

  @Field
  private GenderEnum gender = GenderEnum.NOT_SELECTED;

  @DocumentReference
  @Indexed
  private Institution institution;

  @Override
  public String getUsername() {
    return this.id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.EMPTY_LIST;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
