package nl.marsman.garage.repository;

import nl.marsman.garage.model.Reparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReparationRepository extends JpaRepository<Reparation, Integer> {
    Iterable<Reparation> findAllByAppointmentDate(LocalDate appointmentDate);

}
