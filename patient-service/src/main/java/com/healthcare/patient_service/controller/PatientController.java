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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create")
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto patientDto){
        log.info("Creating Patient");
        PatientDto createdPatient = patientService.createPatient(patientDto);
        return new ResponseEntity<>(createdPatient,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientDto patientDto) {
        log.info("Updating patient with ID: {}", id);
        Optional<PatientDto> updatedPatient = Optional.ofNullable(patientService.updatePatient(id, patientDto));
        return updatedPatient
                .map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        log.info("Deleting patient with ID: {}", id);
        if (patientService.deletePatient(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> findPatientById(@PathVariable Long id) {
        log.info("Finding patient with ID: {}", id);
        Optional<PatientDto> patient = Optional.ofNullable(patientService.findPatient(id));
        return patient
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<PatientDto> findByPhoneOrEmail(@RequestParam Optional<String> phone, @RequestParam Optional<String> email) {
        log.info("Finding patient by phone or email: {} {}", phone.orElse("N/A"), email.orElse("N/A"));
        Optional<PatientDto> patient = patientService.findByEmailOrPhone(phone.orElse(null), email.orElse(null));
        return patient
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}/illness")
    public ResponseEntity<PatientDto> addIllnessToPatient(@PathVariable Long id, @RequestBody List<String> illnesses) {
        log.info("Adding illnesses to patient with ID: {}", id);
        Optional<PatientDto> updatedPatient = patientService.addIllnessToPatient(id, illnesses);
        return updatedPatient
                .map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}




