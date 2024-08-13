package com.healthcare.doctor_service.dto;

import com.healthcare.doctor_service.domain.Doctor;

import java.util.List;

public record DoctorDto(
        String doctorId,
        String fullName,
        String email,
        String phone,
        String address,
        String[] opDays,
        String[] specialization
) {
    public static List<DoctorDto> toDtos(List<Doctor> doctors) {
        return doctors.stream().map(DoctorDto::fromEntity).toList();
    }
    public static DoctorDto fromEntity(Doctor doctor) {
        return new DoctorDto(doctor.getDoctorId(), doctor.getFullName(), doctor.getEmail(),
                doctor.getPhone(), doctor.getAddress(), doctor.getOpDays(),
                doctor.getSpecialization());
    }

    public Doctor toDoctor(){
        return  new Doctor(fullName,email, phone, address,
                specialization, opDays);
    }

    public Doctor toEntity(DoctorDto doctorDto){
        return Doctor.builder().address(doctorDto.address())
                .email(doctorDto.email())
                .fullName(doctorDto.fullName())
                .opDays(doctorDto.opDays())
                .phone(doctorDto.phone())
                .specialization(doctorDto.specialization())
                .build();
    }

}
