package nl.marsman.garage.repository;

import nl.marsman.garage.model.RegistrationPaper;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


@Transactional
public interface RegistrationPaperRepository extends JpaRepository<RegistrationPaper, Long> {
    RegistrationPaper findByFileName(String fileName);
}
