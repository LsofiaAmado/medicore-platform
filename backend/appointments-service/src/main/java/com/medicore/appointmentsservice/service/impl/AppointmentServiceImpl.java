package com.medicore.appointmentsservice.service.impl;

import com.medicore.appointmentsservice.dto.request.CreateAppointmentRequest;
import com.medicore.appointmentsservice.dto.response.AppointmentResponse;
import com.medicore.appointmentsservice.entity.Appointment;
import com.medicore.appointmentsservice.entity.AppointmentStatus;
import com.medicore.appointmentsservice.repository.AppointmentRepository;
import com.medicore.appointmentsservice.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentResponse createAppointment(
            Long patientId,
            CreateAppointmentRequest request
    ) {

        Appointment appointment = Appointment.builder()
                .patientId(patientId)
                .doctorId(request.doctorId())
                .appointmentDate(request.appointmentDate())
                .reason(request.reason())
                .notes(request.notes())
                .status(AppointmentStatus.SCHEDULED)
                .build();

        Appointment savedAppointment =
                appointmentRepository.save(appointment);

        return mapToResponse(savedAppointment);
    }

    @Override
    public List<AppointmentResponse> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AppointmentResponse getAppointmentById(Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Appointment not found"
                        )
                );

        return mapToResponse(appointment);
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByPatient(
            Long patientId
    ) {

        return appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByDoctor(
            Long doctorId
    ) {

        return appointmentRepository.findByDoctorId(doctorId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AppointmentResponse updateAppointmentStatus(
            Long id,
            AppointmentStatus status
    ) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Appointment not found"
                        )
                );

        appointment.setStatus(status);

        Appointment updatedAppointment =
                appointmentRepository.save(appointment);

        return mapToResponse(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {

        if (!appointmentRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Appointment not found"
            );
        }

        appointmentRepository.deleteById(id);
    }

    private AppointmentResponse mapToResponse(
            Appointment appointment
    ) {

        return new AppointmentResponse(
                appointment.getId(),
                appointment.getPatientId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getStatus(),
                appointment.getReason(),
                appointment.getNotes(),
                appointment.getCreatedAt(),
                appointment.getUpdatedAt()
        );
    }

}
