package com.bt.management.microservices.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

  private String id;

  private String firstName;

  private String lastName;

  private String dateOfBirth;

  private Integer age;

  private String dateOfJoining;

  private String email;

  private String phoneNumber;

  private String enrollmentNumber;

  private String username;

  private String password;

  private String serialNumber;

  private BranchEnum branch = BranchEnum.NOT_SELECTED;

  private CoursesEnum course = CoursesEnum.NOT_SELECTED;

  private SemesterEnum semester = SemesterEnum.NOT_SELECTED;

  private YearEnum year = YearEnum.NOT_SELECTED;

  private UserRoleEnum role = UserRoleEnum.NOT_SELECTED;

  private String about;

  private String address;

  private GenderEnum gender = GenderEnum.NOT_SELECTED;

  private String institution = null;
}
