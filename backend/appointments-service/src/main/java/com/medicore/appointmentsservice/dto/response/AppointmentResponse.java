package com.medicore.appointmentsservice.dto.response;

import com.medicore.appointmentsservice.entity.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentResponse(

        Long id,

        Long patientId,

        Long doctorId,

        LocalDateTime appointmentDate,

        AppointmentStatus status,

        String reason,

        String notes,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
