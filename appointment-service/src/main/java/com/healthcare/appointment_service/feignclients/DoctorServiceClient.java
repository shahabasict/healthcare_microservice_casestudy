package com.healthcare.appointment_service.feignclients;

import com.healthcare.appointment_service.dto.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor_service",url = "http:/localhost:8200/doctor")
public interface DoctorServiceClient {

    @GetMapping("/{doctorId}")
    public DoctorDto getDoctorById(@PathVariable String doctorId);

}
