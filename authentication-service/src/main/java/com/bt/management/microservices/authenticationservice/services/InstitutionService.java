package com.bt.management.microservices.authenticationservice.services;

import com.bt.management.microservices.authenticationservice.exceptions.ResourceAlreadyExistsException;
import com.bt.management.microservices.authenticationservice.exceptions.ResourceNotFoundException;
import com.bt.management.microservices.authenticationservice.models.Institution;
import com.bt.management.microservices.authenticationservice.repositories.InstitutionRepository;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.beans.factory.annotation.Autowired;
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
    System.out.println(institutionDB.isEmpty());
    if (institutionDB.isEmpty() == false) {
      throw new ResourceAlreadyExistsException(
        "Institution",
        "email",
        institutionDB.get().getEmail()
      );
    }

    System.out.println("institutionDB" + institutionDB);
    return institutionRepository.save(institute);
  }

  public Institution updateInstitutes(
    Institution institution,
    String instituteId
  ) {
    Institution instituteDB = institutionRepository
      .findById(instituteId)
      .orElseThrow(() ->
        new ResourceNotFoundException("Institution", "Id", instituteId)
      );

    // System.out.println(institution.getEmail().equals(instituteDB.getEmail()));
    Boolean isEmailExists = instituteDB
      .getEmail()
      .equals(institution.getEmail());
    Boolean isCodeExists = instituteDB.getCode().equals(institution.getCode());

    if (isEmailExists || isCodeExists) {
      String fieldVal = isEmailExists
        ? institution.getEmail().toString()
        : institution.getCode().toString();
      String name = isEmailExists ? "email" : "code";

      throw new ResourceAlreadyExistsException("Institution", name, fieldVal);
    }

    if (
      instituteDB.getContactNumber1().equals(institution.getContactNumber1()) ||
      instituteDB.getContactNumber2().equals(institution.getContactNumber2())
    ) {
      String contactNumber;
      Boolean contactNumber1 = institution
        .getContactNumber1()
        .equals(instituteDB.getContactNumber1());

      contactNumber =
        contactNumber1 == true
          ? institution.getContactNumber1().toString()
          : institution.getContactNumber2().toString();

      throw new ResourceAlreadyExistsException(
        "Institution",
        "Contact No.",
        contactNumber
      );
    }
    System.out.println(instituteDB);
    System.out.println(institution);
    institution.setId(instituteId);
    return institutionRepository.save(institution);
  }

  public Optional<Institution> deleteInstitutes(String institutionId) {
    Optional<Institution> institution = institutionRepository.findById(
      institutionId
    );

    if (institution.isEmpty()) {
      throw new ResourceNotFoundException("Institution", "Id", institutionId);
    }
    institutionRepository.deleteById(institutionId);
    return institution;
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
