package nl.marsman.garage.service;

import nl.marsman.garage.dto.ReparationRequestDto;
import nl.marsman.garage.exception.RecordNotFoundException;
import nl.marsman.garage.model.Car;
import nl.marsman.garage.model.CarPart;
import nl.marsman.garage.model.Customer;
import nl.marsman.garage.model.Reparation;
import nl.marsman.garage.repository.CarPartRepository;
import nl.marsman.garage.repository.ReparationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReparationService {

    @Autowired
    private ReparationRepository reparationRepository;

    @Autowired
    private CarPartRepository carPartRepository;

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
        Reparation newReparation = reparationRepository.save(reparation);
        return newReparation.getId();
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

    public void  addReparationCarPart(int id, CarPart carPart) {
        Optional<Reparation> optionalReparation = reparationRepository.findById(id);

        if (optionalReparation.isPresent()) {
            Reparation reparation = optionalReparation.get();
            List<CarPart> usedCarParts = reparation.getReparationParts();

            carPartRepository.save(carPart);

            usedCarParts.add(carPart);
            reparationRepository.save(reparation);

        }
        else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }

    public List<CarPart> getReparationCarParts(int id){
        Optional<Reparation> optionalReparation = reparationRepository.findById(id);

        if (optionalReparation.isPresent()) {
            Reparation reparation = optionalReparation.get();
            return reparation.getReparationParts();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }



}
