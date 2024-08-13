package com.healthcare.appointment_service.controller;

import com.healthcare.appointment_service.domain.Appointment;
import com.healthcare.appointment_service.dto.AppoinmentDto;
import com.healthcare.appointment_service.dto.DoctorDto;
import com.healthcare.appointment_service.dto.PatientDto;
import com.healthcare.appointment_service.serivce.AppoinmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class Controller {

    private final AppoinmentService appoinmentService;


    public Controller(AppoinmentService appoinmentService) {
        this.appoinmentService = appoinmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<AppoinmentDto> createAppointment(
           @RequestBody AppoinmentDto Dto){
        PatientDto patient = appoinmentService.getPatientById(String.valueOf(Dto.patientId()));

        if(patient==null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }

        DoctorDto doctor = appoinmentService.getDoctorById(Dto.doctorId());

        if (doctor==null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }

        var appointment = Dto.toAppointment();
        appointment.setPatientName(patient.fullName());
        appointment.setPatientId(patient.paientId());
        appointment.setDoctorName(doctor.fullName());
        var response = appoinmentService.createAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(AppoinmentDto.fromAppointment(response));
    }




}
