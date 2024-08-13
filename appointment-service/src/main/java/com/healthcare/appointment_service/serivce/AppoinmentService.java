package com.healthcare.appointment_service.serivce;

import com.healthcare.appointment_service.domain.Appointment;
import com.healthcare.appointment_service.dto.DoctorDto;
import com.healthcare.appointment_service.dto.PatientDto;

import java.util.List;

public interface AppoinmentService {

    Appointment createAppointment(Appointment appointment);

    public Appointment getAppointmentById(String id);

    List<Appointment> findAllByPatientId(String patientId);

    List<Appointment> findAllByDoctorId(String doctorId);

    List<Appointment> findAllByPatientIdAndDoctorId(String patientId, String doctorId);

    PatientDto getPatientById(String patientId);

    DoctorDto getDoctorById(String doctorId);
}
