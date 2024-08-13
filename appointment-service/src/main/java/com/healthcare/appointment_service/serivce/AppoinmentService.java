package com.healthcare.appointment_service.serivce;

import com.healthcare.appointment_service.domain.Appoinment;

import java.util.List;

public interface AppoinmentService {

    void createAppoinment(Appoinment appoinment);

    public Appoinment getAppointmentById(int id);

    List<Appoinment> findAllByPatientId(String patientId);

    List<Appoinment> findAllByDoctorId(String doctorId);
}
