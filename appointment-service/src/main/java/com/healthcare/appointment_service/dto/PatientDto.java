package com.healthcare.appointment_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PatientDto(long paientId,String fullName, String phone) {
}
