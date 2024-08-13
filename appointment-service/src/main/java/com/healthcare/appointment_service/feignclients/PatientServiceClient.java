package com.healthcare.appointment_service.feignclients;

import com.healthcare.appointment_service.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient_service",url = "")
public interface PatientServiceClient {

    @GetMapping("/{id}")
    public PatientDto getPatientById(@PathVariable long id);

}
