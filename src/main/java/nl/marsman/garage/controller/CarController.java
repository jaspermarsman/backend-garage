package nl.marsman.garage.controller;

import nl.marsman.garage.dto.CarRequestDto;
import nl.marsman.garage.model.Car;
import nl.marsman.garage.model.RegistrationPaper;
import nl.marsman.garage.model.Reparation;
import nl.marsman.garage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    public ResponseEntity<Object> getCars(@RequestParam(name="licensePlate", defaultValue = "") String licensePlate) {
        return ResponseEntity.ok(carService.getCars(licensePlate));
    }

    @GetMapping(value = "/cars/{id}")
    public ResponseEntity<Object> getCar(@PathVariable int id) {
        return ResponseEntity.ok(carService.getCar(id));
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") int id) {
        carService.deleteCar(id);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/cars")
    public ResponseEntity<Object> addCar(@RequestBody CarRequestDto carRequestDto) {
        int newId = carService.addCar(carRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/cars/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable int id, @RequestBody Car car) {
        carService.updateCar(id, car);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/cars/{id}")
    public ResponseEntity<Object> partialUpdateCar(@PathVariable int id, @RequestBody Car car) {
        carService.partialUpdateCar(id, car);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/cars/{id}/reparations")
    public ResponseEntity<Object> getCarReparations(@PathVariable int id) {
        return ResponseEntity.ok(carService.getCarReparations(id));
    }

    @PostMapping(value = "/cars/{id}/reparations")
    public ResponseEntity<Object> addCarReparation(@PathVariable int id, @RequestBody Reparation reparation) {
        carService.addCarReparation(id, reparation);
        return  ResponseEntity.created(null).build();
    }




}
