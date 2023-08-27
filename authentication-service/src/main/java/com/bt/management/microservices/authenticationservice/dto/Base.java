package com.bt.management.microservices.authenticationservice.dto;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Base {

  @Field
  private Boolean active = true;

  @Field
  private Boolean deleted = false;

  // Timestamp
  @CreatedDate
  @Field("created_At")
  private Instant CreatedDate = Instant.now();

  @LastModifiedDate
  @Field("updated_At")
  private Instant LastModifiedDate = Instant.now();

  @Version
  private Integer version;
}
