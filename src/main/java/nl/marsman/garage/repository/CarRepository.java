package nl.marsman.garage.repository;

import nl.marsman.garage.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {
    Iterable<Car> findAllByLicensePlateContainingIgnoreCase(String secondName);
}
