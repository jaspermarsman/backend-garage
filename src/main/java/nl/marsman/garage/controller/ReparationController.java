package nl.marsman.garage.controller;

import nl.marsman.garage.dto.ReparationRequestDto;
import nl.marsman.garage.model.Car;
import nl.marsman.garage.service.ReparationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
public class ReparationController {

    @Autowired
    private ReparationService reparationService;

    @GetMapping(value = "/reparations")
    public ResponseEntity<Object> getReparations(@RequestParam(name="appointmentDate", defaultValue = "") LocalDate appointmentDate) {
        return ResponseEntity.ok(reparationService.getReparations(appointmentDate));
    }

    @GetMapping(value = "/reparations/{id}")
    public ResponseEntity<Object> getReparation(@PathVariable int id) {
        return ResponseEntity.ok(reparationService.getReparation(id));
    }

    @DeleteMapping(value = "/reparations/{id}")
    public ResponseEntity<Object> deleteReparation(@PathVariable("id") int id) {
        reparationService.deleteReparation(id);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/reparations")
    public ResponseEntity<Object> addReparation(@RequestBody ReparationRequestDto reparationRequestDto) {
        int newId = reparationService.addReparation(reparationRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/reparations/{id}")
    public ResponseEntity<Object> updateReparation(@PathVariable int id, @RequestBody ReparationRequestDto reparationRequestDto) {
        reparationService.updateReparation(id, reparationRequestDto);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/reparations/{id}")
    public ResponseEntity<Object> partialUpdateReparation(@PathVariable int id, @RequestBody ReparationRequestDto reparationRequestDto) {
        reparationService.partialUpdateReparation(id, reparationRequestDto);

        return ResponseEntity.noContent().build();
    }

}
