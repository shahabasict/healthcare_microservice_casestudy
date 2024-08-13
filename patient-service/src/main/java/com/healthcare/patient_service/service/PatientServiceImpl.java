package com.healthcare.patient_service.service;

import com.healthcare.patient_service.convertor.PatientConverter;
import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.domain.PreExistingIllness;
import com.healthcare.patient_service.dto.PatientDto;
import com.healthcare.patient_service.exception.PatientNotFoundException;
import com.healthcare.patient_service.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private final PatientConverter patientConverter;

    public PatientServiceImpl(PatientRepository patientRepository,PatientConverter patientConverter) {
        this.patientRepository = patientRepository;
        this.patientConverter = patientConverter;
    }


    @Override
    public PatientDto createPatient(PatientDto patientDto) {

        log.info("Creating Patient : {}",patientDto);
        Patient patient = patientConverter.toEntity(patientDto);
        Patient savedPatient = patientRepository.save(patient);

        return patientConverter.toDto(savedPatient);
    }

    @Override
    public boolean deletePatient(long id) {
        log.info("Deleting Patient With ID : "+id);
        if (!patientRepository.existsById(id)){
            throw new PatientNotFoundException("Patient not found with id : "+id);
        }
        patientRepository.deleteById(id);
        return false;
    }

    @Override
    public PatientDto updatePatient(long id, PatientDto patientDto) {
        log.info("Updating Patient with Id : {}",id);
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found with id : "+id));
        Patient updatedPatient = patientConverter.updateEntity(existingPatient,patientDto);
        Patient savedPatient = patientRepository.save(updatedPatient);

        return patientConverter.toDto(savedPatient);
    }

    @Override
    public PatientDto findPatient(long id) {
        log.info("Finding patient"+id);
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException("Patient Not Found"));

        return patientConverter.toDto(patient);
    }

    @Override
    public Optional<PatientDto> findByEmailOrPhone(String phone, String email) {
        log.info("Finding the Patients by phone {} or mail {}",phone,email);
        List<Patient> patients = patientRepository.findByPhoneOrEmail(phone,email);
        return patientConverter.toDtoList(patients);
    }

    @Override
    public Optional<PatientDto> addIllnessToPatient(long id, List<String> illnesses) {
        log.info("Adding illnesses to patient with id: {}", id);
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));
        List<PreExistingIllness> illnessList = illnesses.stream()
                .map(illness -> new PreExistingIllness(0, illness)) // Adjust as needed
                .toList();
        patient.getPreExistingIllnesses().addAll(illnessList);
        patientRepository.save(patient);
        return null;
    }
}
