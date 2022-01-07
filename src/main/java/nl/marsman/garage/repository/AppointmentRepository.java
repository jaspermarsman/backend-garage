package nl.marsman.garage.repository;

import nl.marsman.garage.model.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    Iterable<Appointment>findAllByAppointmentDate_Chronology(LocalDate appointmentDate);
}
