package com.healthcare.patient_service.dto;

import com.healthcare.patient_service.domain.PreExistingIllness;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record PatientDto(

        long id,

        @NotBlank(message = "Name is Required")
        @Length(min = 2)
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Full name must not contain digits")
        String fullName,

        @NotBlank(message = "Required")
        @Email(message = "Should be a proper Email")
        String email,

        @NotBlank(message = "Required")
        String address,

        @NotBlank(message = "Required")
        @Pattern(regexp = "\\d{10}",message = "phone should be 10 digits in length")
        String phone,

        @NotBlank(message = "Required")
        @Past(message = "DOB should be in past")
        LocalDate dob,

        List<@NotEmpty String> preExistingIllnesses

        ){
}
