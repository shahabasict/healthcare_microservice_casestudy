package com.healthcare.patient_service.controller;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.dto.PatientDto;
import com.healthcare.patient_service.service.PatientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("create")
    public Patient createPatient(@Valid @RequestBody PatientDto patientDto){
        return patientService.createPatient(patientDto);
    }


}