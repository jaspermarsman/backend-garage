package nl.marsman.garage.service;

import nl.marsman.garage.dto.ReparationRequestDto;
import nl.marsman.garage.exception.RecordNotFoundException;
import nl.marsman.garage.model.Reparation;
import nl.marsman.garage.repository.ReparationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReparationService {

    @Autowired
    private ReparationRepository reparationRepository;

    public Iterable<Reparation> getReparations(LocalDate appointmentDate) {

        if (appointmentDate == null) {
            return reparationRepository.findAll();
        } else {
            return reparationRepository.findAllByAppointmentDate(appointmentDate);
        }

    }

    public Reparation getReparation(int id) {
        Optional<Reparation> optionalReparation = reparationRepository.findById(id);

        if (optionalReparation.isPresent()) {
            return optionalReparation.get();
        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void deleteReparation(int id) {
        reparationRepository.deleteById(id);
    }

    public int addReparation(ReparationRequestDto reparationRequestDto) {

        Reparation reparation = new Reparation();
        reparation.setAppointmentDate(reparationRequestDto.getAppointmentDate());
        Reparation newRepair = reparationRepository.save(reparation);
        return newRepair.getId();
    }

    public void updateReparation(int id, ReparationRequestDto reparationRequestDto) {

        Optional<Reparation> optionalReparation = reparationRepository.findById(id);

        if (optionalReparation.isPresent()) {

            Reparation reparation = optionalReparation.get();
            reparation.setAppointmentDate(reparationRequestDto.getAppointmentDate());
            reparationRepository.save(reparation);

        } else {

            throw new RecordNotFoundException("ID does not exist!!!");
        }

    }

    public void partialUpdateReparation(int id, ReparationRequestDto reparationRequestDto) {

        Optional<Reparation> optionalReparation = reparationRepository.findById(id);

        if (optionalReparation.isPresent()) {
            Reparation reparation = optionalReparation.get();

            if(reparationRequestDto.getAppointmentDate() != null) {

                reparation.setAppointmentDate(reparationRequestDto.getAppointmentDate());
            }


            reparationRepository.save(reparation);

        } else {

            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

}
