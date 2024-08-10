package com.healthcare.patient_service.service;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.domain.PreExistingIllness;
import com.healthcare.patient_service.dto.PatientDto;
import com.healthcare.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setFullName(patientDto.fullName());
        patient.setEmail(patientDto.email());
        patient.setAddress(patientDto.address());
        patient.setPhone(patientDto.phone());
        patient.setDob(patientDto.dob());
        patient.setPreExistingIllnesses(illnessList(patientDto.preExistingIllnesses()));
        return patientRepository.save(patient);
    }

    public PreExistingIllness createIllness(PreExistingIllness preExistingIllness){
        return preExistingIllness;
    }

    public List<PreExistingIllness> illnessList(List<PreExistingIllness> preExistingIllness){
        return preExistingIllness.stream().map(this::createIllness).toList();
    }



}
