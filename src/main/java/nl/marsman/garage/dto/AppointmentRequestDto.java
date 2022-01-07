package nl.marsman.garage.dto;

import java.time.LocalDate;

public class AppointmentRequestDto {
    private LocalDate appointmentDate;

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
}
