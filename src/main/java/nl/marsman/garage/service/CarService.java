package nl.marsman.garage.service;

import nl.marsman.garage.dto.CarRequestDto;
import nl.marsman.garage.dto.CustomerRequestDto;
import nl.marsman.garage.exception.RecordNotFoundException;
import nl.marsman.garage.model.Car;
import nl.marsman.garage.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> getCars(String licensePlate) {
        if (licensePlate.isEmpty()) {
            return carRepository.findAll();
        }
        else {
            return carRepository.findAllByLicensePlateContainingIgnoreCase(licensePlate);
        }
    }


    public Car getCar(int id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            return optionalCar.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }

    }

    public void deleteCar(int id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }


    public int addCar(CarRequestDto carRequestDto) {

        Car car = new Car();
        car.setBrand(carRequestDto.getBrand());
        car.setModel(carRequestDto.getModel());
        car.setModelYear(carRequestDto.getModelYear());
        car.setLicensePlate(carRequestDto.getLicensePlate());

        Car newCar = carRepository.save(car);
        return newCar.getId();
    }

//    public void updateCar(int id, Car car) {
//        Optional<Car> optionalCar = carRepository.findById(id);
//
//        if (optionalCar.isPresent()) {
//            Car storedCar = optionalCar.get();
//
//            car.setId(storedCar.getId());
//            carRepository.save(car);
//        }
//        else {
//            throw new RecordNotFoundException("ID does not exist!!!");
//        }
//    }
//
//    public void partialUpdateCar(int id, Car car) {
//        Optional<Car> optionalCar = carRepository.findById(id);
//
//        if (optionalCar.isPresent()) {
//            Car storedCar = carRepository.findById(id).orElse(null);
//
//            if (car.getFirstName()!=null && !car.getFirstName().isEmpty()) {
//                storedCar.setFirstName(car.getFirstName());
//            }
//            if (car.getSecondName()!=null && !car.getSecondName().isEmpty()) {
//                storedCar.setSecondName(car.getSecondName());
//            }
//
//            carRepository.save(car);
//
//        }
//        else {
//            throw new RecordNotFoundException("ID does not exist!!!");
//        }
//    }



}
