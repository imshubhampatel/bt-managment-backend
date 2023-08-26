package com.bt.management.microservices.authenticationservice.controllers;

import com.bt.management.microservices.authenticationservice.models.Institution;
import com.bt.management.microservices.authenticationservice.services.HelperService;
import com.bt.management.microservices.authenticationservice.services.InstitutionService;
import java.util.List;
import java.util.Optional;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/institution")
public class InstitutionController {

  @Autowired
  InstitutionService institutionService;

  @Autowired
  HelperService helperService;

  @GetMapping("/get-all-institution")
  public ResponseEntity<Object> getAllInstitution() {
    List<Institution> allInstitutions = institutionService.getAllInstitutions();
    return helperService.generateResponse(
      "Fetched data successfully",
      HttpStatus.SC_OK,
      allInstitutions
    );
  }

  @PostMapping("/register-institution")
  public Institution registerInstitutes(@RequestBody Institution instituteReq) {
    System.out.println("collegeRequest" + instituteReq);
    return institutionService.registerInstitution(instituteReq);
  }

  @PutMapping("/update-institution/{instituteId}")
  public Institution updateInstitutes(
    @PathVariable String instituteId,
    @RequestBody Institution instituteReq
  ) {
    return institutionService.updateInstitutes(instituteReq, instituteId);
  }

  @DeleteMapping("/delete-institution/{instituteId}")
  public ResponseEntity<Object> deleteInstitutes(
    @PathVariable String instituteId
  ) {
    Optional<Institution> institution = institutionService.deleteInstitutes(
      instituteId
    );
    return helperService.generateResponse(
      "Institution is Deleted Successfully",
      HttpStatus.SC_ACCEPTED,
      institution
    );
  }
}
