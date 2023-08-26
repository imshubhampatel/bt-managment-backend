package com.bt.management.microservices.authenticationservice.repositories;

import com.bt.management.microservices.authenticationservice.models.Institution;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InstitutionRepository
  extends MongoRepository<Institution, String> {
  Optional<Institution> findOneByEmail(String email);
}