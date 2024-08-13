package com.healthcare.patient_service.convertor;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.dto.PatientDto;

import java.util.List;

public interface PatientConverter {

    Patient toEntity(PatientDto patientDto);

    PatientDto toDto(Patient patient);

    List<PatientDto> toDtoList(List<Patient> patients);

    Patient updateEntity(Patient existingPatient,PatientDto patientDto);
}
