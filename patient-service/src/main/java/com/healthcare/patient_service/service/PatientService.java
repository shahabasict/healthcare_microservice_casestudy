package com.healthcare.patient_service.service;

import com.healthcare.patient_service.dto.PatientDto;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    PatientDto createPatient(PatientDto patientDto);

    boolean deletePatient(long id);

    PatientDto updatePatient(long id, PatientDto patientDto);

    PatientDto findPatient(long id);

    Optional<PatientDto> findByEmailOrPhone(String phone, String Email);

    Optional<PatientDto> addIllnessToPatient(long id, List<String> illnesses);


}
