package nl.marsman.garage.repository;


import nl.marsman.garage.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Iterable<Customer> findAllBySecondNameContainingIgnoreCase(String secondName);
}

