package nl.marsman.garage.service;

import nl.marsman.garage.dto.CarPartRequestDto;
import nl.marsman.garage.exception.RecordNotFoundException;
import nl.marsman.garage.model.CarPart;
import nl.marsman.garage.repository.CarPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarPartService {

    @Autowired
    private CarPartRepository carPartRepository;

    public Iterable<CarPart> getAllInventoryItems(String description) {
        if (description.isEmpty()) {
            return carPartRepository.findAll();
        }
        else {
            return carPartRepository.findAllByDescriptionContainingIgnoreCase(description);
        }
    }

    public CarPart getInventoryItem(int id) {
        Optional<CarPart> optionalInventoryItem = carPartRepository.findById(id);

        if (optionalInventoryItem.isPresent()) {
            return optionalInventoryItem.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void deleteInventoryItem(int id) {
        if (carPartRepository.existsById(id)) {
            carPartRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public int addInventoryItem(CarPartRequestDto carPartRequestDto) {

        CarPart carPart = new CarPart();
        carPart.setDescription(carPartRequestDto.getDescription());
        carPart.setPrice(carPartRequestDto.getPrice());
        carPart.setAmountInStock(carPartRequestDto.getAmountInStock());

        CarPart newCarPart = carPartRepository.save(carPart);
        return newCarPart.getId();
    }

    public void updateInventoryItem(int id, CarPart carPart) {
        Optional<CarPart> optionalInventoryItem = carPartRepository.findById(id);

        if (optionalInventoryItem.isPresent()) {
            CarPart storedCarPart = optionalInventoryItem.get();

            carPart.setId(storedCarPart.getId());
            carPartRepository.save(carPart);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }




}
