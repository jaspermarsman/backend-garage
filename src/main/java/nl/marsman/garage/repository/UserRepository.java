package nl.marsman.garage.repository;

import nl.marsman.garage.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
