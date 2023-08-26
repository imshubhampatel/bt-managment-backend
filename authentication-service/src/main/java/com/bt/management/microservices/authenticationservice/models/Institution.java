package com.bt.management.microservices.authenticationservice.models;

import com.bt.management.microservices.authenticationservice.dto.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Institution")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Institution {

  @Id
  private String id;

  @Indexed(unique = true)
  private String name;

  @Indexed(unique = true)
  private String email;

  @Indexed(unique = true)
  private String code;

  @Indexed(unique = true)
  private String contactNumber1;

  @Indexed(unique = true)
  private String contactNumber2;

  @Field
  private String address;

  @Field
  private String state;

  @Field
  private String city;

  @Field
  private String zipCode;

  @Field
  private String openingTime;

  @Field
  private String closingTime;

  @Field
  private StatusEnum status = StatusEnum.OPEN;
}
