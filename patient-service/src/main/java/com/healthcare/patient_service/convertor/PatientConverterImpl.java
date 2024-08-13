package com.healthcare.patient_service.convertor;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.domain.PreExistingIllness;
import com.healthcare.patient_service.dto.PatientDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientConverterImpl implements PatientConverter{



    @Override
    public Patient toEntity(PatientDto patientDto) {
        return new Patient(patientDto.id(),
                patientDto.fullName(),
                patientDto.email(),
                patientDto.address(),
                patientDto.phone(),
                patientDto.dob(),
                patientDto.preExistingIllnesses().stream()
                        .map(illness -> new PreExistingIllness(0,illness))
                        .toList());
    }

    @Override
    public PatientDto toDto(Patient patient) {
        return new PatientDto(patient.getId(),
                patient.getFullName(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getPhone(),
                patient.getDob(),
                patient.getPreExistingIllnesses().stream()
                        .map(PreExistingIllness::getIllness)
                        .toList()
        );



    }

    @Override
    public List<PatientDto> toDtoList(List<Patient> patients) {
        return patients.stream().map(this::toDto).toList();
    }

    @Override
    public Patient updateEntity(Patient existingPatient, PatientDto patientDto) {
        existingPatient.setFullName(patientDto.fullName());
        existingPatient.setEmail(patientDto.email());
        existingPatient.setAddress(patientDto.address());
        existingPatient.setPhone(patientDto.phone());
        existingPatient.setDob(patientDto.dob());

        if (patientDto.preExistingIllnesses() != null){
            List<PreExistingIllness> updatedIllness = patientDto.preExistingIllnesses().stream().map(illness->
                    new PreExistingIllness(0,illness)).toList();

            existingPatient.getPreExistingIllnesses().clear();
            existingPatient.getPreExistingIllnesses().addAll(updatedIllness);
        }

        return existingPatient;
    }
}
