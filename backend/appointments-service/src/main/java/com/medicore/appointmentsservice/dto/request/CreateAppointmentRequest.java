package com.medicore.appointmentsservice.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateAppointmentRequest(

        @NotNull(message = "Doctor ID is required")
        Long doctorId,

        @NotNull(message = "Appointment date is required")
        @Future(message = "Appointment date must be in the future")
        LocalDateTime appointmentDate,

        @NotBlank(message = "Reason is required")
        @Size(max = 500, message = "Reason must not exceed 500 characters")
        String reason,

        @Size(max = 1000, message = "Notes must not exceed 1000 characters")
        String notes

) {
}