package nl.marsman.garage.repository;

import nl.marsman.garage.model.Klant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlantRepository extends JpaRepository<Klant, Integer> {
}

