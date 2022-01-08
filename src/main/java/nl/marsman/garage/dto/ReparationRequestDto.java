package nl.marsman.garage.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReparationRequestDto {

    @Future(message = "{error.appointmentDate.notInPast}")
    @NotNull(message = "{error.appointmentDate.notnull}" )
    private LocalDate appointmentDate;

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
