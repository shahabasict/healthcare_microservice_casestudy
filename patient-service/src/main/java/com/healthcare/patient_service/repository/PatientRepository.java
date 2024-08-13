package com.healthcare.patient_service.repository;

import com.healthcare.patient_service.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {


    List<Patient> findByPhoneOrEmail(String phone, String email);
}
