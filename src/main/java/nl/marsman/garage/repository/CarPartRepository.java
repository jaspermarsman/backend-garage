package nl.marsman.garage.repository;

import nl.marsman.garage.model.CarPart;
import org.springframework.data.repository.CrudRepository;

public interface CarPartRepository extends CrudRepository<CarPart, Integer> {
    Iterable<CarPart> findAllByDescriptionContainingIgnoreCase(String description);
}
