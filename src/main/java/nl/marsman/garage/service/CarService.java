package nl.marsman.garage.service;

import nl.marsman.garage.dto.CarRequestDto;
import nl.marsman.garage.exception.RecordNotFoundException;
import nl.marsman.garage.model.Car;
import nl.marsman.garage.model.RegistrationPaper;
import nl.marsman.garage.model.Reparation;
import nl.marsman.garage.repository.CarRepository;

import nl.marsman.garage.repository.RegistrationPaperRepository;
import nl.marsman.garage.repository.ReparationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RegistrationPaperRepository registrationPaperRepository;

    @Autowired
    private ReparationRepository reparationRepository;

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
        car.setLicensePlate(carRequestDto.getLicensePlate());

        Car newCar = carRepository.save(car);
        return newCar.getId();
    }

    public void updateCar(int id, Car car) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car storedCar = optionalCar.get();

            car.setId(storedCar.getId());
            carRepository.save(car);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void partialUpdateCar(int id, Car car) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car storedCar = carRepository.findById(id).orElse(null);

            if (car.getBrand()!=null && !car.getBrand().isEmpty()) {
                storedCar.setBrand(car.getBrand());
            }
            if (car.getModel()!=null && !car.getModel().isEmpty()) {
                storedCar.setModel(car.getModel());
            }
            if (car.getLicensePlate()!=null && !car.getLicensePlate().isEmpty()) {
                storedCar.setLicensePlate(car.getLicensePlate());
            }

            carRepository.save(storedCar);

        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void  addCarReparation(int id, Reparation reparation) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            List<Reparation> reparations = car.getReparations();

            reparationRepository.save(reparation);

            reparations.add(reparation);
            carRepository.save(car);

        }
        else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }

    public List<Reparation> getCarReparations(int id){
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            return car.getReparations();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }








}
