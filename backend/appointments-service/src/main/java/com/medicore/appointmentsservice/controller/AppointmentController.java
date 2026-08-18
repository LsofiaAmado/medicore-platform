
package com.medicore.appointmentsservice.controller;

import com.medicore.appointmentsservice.dto.request.CreateAppointmentRequest;
import com.medicore.appointmentsservice.dto.response.AppointmentResponse;
import com.medicore.appointmentsservice.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @PreAuthorize("hasRole('PATIENT') or hasRole('ADMIN')")
    public ResponseEntity<AppointmentResponse> createAppointment(
            @Valid @RequestBody CreateAppointmentRequest request
    ) {

        // Temporalmente recibimos el patientId por header.
        // Después lo reemplazaremos por el userId del JWT.
        Long patientId = 1L;

        return ResponseEntity.ok(
                appointmentService.createAppointment(
                        patientId,
                        request
                )
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {

        return ResponseEntity.ok(
                appointmentService.getAllAppointments()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('PATIENT')")
    public ResponseEntity<AppointmentResponse> getAppointmentById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                appointmentService.getAppointmentById(id)
        );
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('PATIENT') and #patientId == authentication.principal)")
    public ResponseEntity<List<AppointmentResponse>> getPatientAppointments(
            @PathVariable Long patientId
    ) {

        return ResponseEntity.ok(
                appointmentService.getAppointmentsByPatient(patientId)
        );
    }

    @GetMapping("/doctor/{doctorId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('DOCTOR') and #doctorId == authentication.principal)")
    public ResponseEntity<List<AppointmentResponse>> getDoctorAppointments(
            @PathVariable Long doctorId
    ) {

        return ResponseEntity.ok(
                appointmentService.getAppointmentsByDoctor(doctorId)
        );
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<AppointmentResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {

        return ResponseEntity.ok(
                appointmentService.updateAppointmentStatus(
                        id,
                        com.medicore.appointmentsservice.entity.AppointmentStatus
                                .valueOf(status.toUpperCase())
                )
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable Long id
    ) {

        appointmentService.deleteAppointment(id);

        return ResponseEntity.noContent().build();
    }
}