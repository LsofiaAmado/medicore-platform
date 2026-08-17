package com.medicore.appointmentsservice.service;

import com.medicore.appointmentsservice.dto.request.CreateAppointmentRequest;
import com.medicore.appointmentsservice.dto.response.AppointmentResponse;
import com.medicore.appointmentsservice.entity.AppointmentStatus;

import java.util.List;

public interface AppointmentService {

    AppointmentResponse createAppointment(
            Long patientId,
            CreateAppointmentRequest request
    );

    List<AppointmentResponse> getAllAppointments();

    AppointmentResponse getAppointmentById(Long id);

    List<AppointmentResponse> getAppointmentsByPatient(
            Long patientId
    );

    List<AppointmentResponse> getAppointmentsByDoctor(
            Long doctorId
    );

    AppointmentResponse updateAppointmentStatus(
            Long id,
            AppointmentStatus status
    );

    void deleteAppointment(Long id);

}
