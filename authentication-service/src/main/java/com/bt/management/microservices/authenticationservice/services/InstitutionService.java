package com.bt.management.microservices.authenticationservice.services;

import com.bt.management.microservices.authenticationservice.exceptions.ResourceNotFoundException;
import com.bt.management.microservices.authenticationservice.models.Institution;
import com.bt.management.microservices.authenticationservice.repositories.InstitutionRepository;
import java.util.List;
import java.util.Optional;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InstitutionService {

  @Autowired
  InstitutionRepository institutionRepository;

  @Autowired
  HelperService helperService;

  public Institution registerInstitution(Institution institutionReq) {
    Institution institute = convertToInstitution(institutionReq);
    Optional<Institution> institutionDB = institutionRepository.findOneByEmail(
      institute.getEmail()
    );

    System.out.println("institutionDB" + institutionDB);

    // if (institutionDB.isEmpty() == true) {
    //   return helperService.generateResponse("Institution already registered", HttpStatus.SC_FORBIDDEN, institutionDB)
    // }

    return institutionRepository.save(institute);
  }

  public Institution updateInstitutes(
    Institution institution,
    String instituteId
  ) {
    Institution institute = institutionRepository
      .findById(instituteId)
      .orElseThrow(() ->
        new ResourceNotFoundException("Institution", "id", instituteId)
      );

    System.out.println(institute);
    institution.setId(instituteId);
    return institutionRepository.save(institution);
  }

  public void deleteInstitutes(String institutionId) {
    institutionRepository.deleteById(institutionId);
  }

  public List<Institution> getAllInstitutions() {
    return institutionRepository.findAll();
  }

  private Institution convertToInstitution(Institution institutionReq) {
    Institution institution = new Institution();
    institution.setName(institutionReq.getName());
    institution.setEmail(institutionReq.getEmail());
    institution.setCode(institutionReq.getCode());
    institution.setAddress(institutionReq.getAddress());
    institution.setState(institutionReq.getState());
    institution.setCity(institutionReq.getCity());
    institution.setClosingTime(institutionReq.getClosingTime());
    institution.setOpeningTime(institutionReq.getOpeningTime());
    institution.setContactNumber1(institutionReq.getContactNumber1());
    institution.setContactNumber2(institutionReq.getContactNumber2());
    institution.setZipCode(institutionReq.getZipCode());
    institution.setStatus(institutionReq.getStatus());
    System.out.println("institution" + institution);
    return institution;
  }
}
