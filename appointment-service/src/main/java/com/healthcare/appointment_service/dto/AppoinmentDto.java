package com.healthcare.appointment_service.dto;

import com.healthcare.appointment_service.domain.Appointment;

public record AppoinmentDto(
        String id,
        String appointmentDate,
        String appointmentTime,
        long patientId,
        String patientName,
        String patientNumber,
        String doctorId,
        String doctorName,
        String status) {

    public static AppoinmentDto fromAppointment(Appointment response){
        return new AppoinmentDto(
                response.getId(),
                response.getAppointmentDate(),
                response.getAppointmentTime(),
                response.getPatientId(),
                response.getPatientName(),
                response.getPatientNumber(),
                response.getDoctorId(),
                response.getDoctorName(),
                response.getStatus()
                );
    }

    public Appointment toAppointment(){
        return new Appointment( appointmentDate, appointmentTime, Long.parseLong(String.valueOf(patientId)),
                patientName, patientNumber, doctorId, doctorName, status);
    }



}
