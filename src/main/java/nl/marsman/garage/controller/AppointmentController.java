package nl.marsman.garage.controller;

import nl.marsman.garage.dto.AppointmentRequestDto;
import nl.marsman.garage.dto.CarRequestDto;
import nl.marsman.garage.model.Appointment;
import nl.marsman.garage.model.Car;
import nl.marsman.garage.service.AppointmentService;
import nl.marsman.garage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/appointments")
    public ResponseEntity<Object> getAppointments(@RequestParam(name="appointmentDate", defaultValue = "") LocalDate appointmentDate) {
        return ResponseEntity.ok(appointmentService.getAppointments(appointmentDate));
    }

    @GetMapping(value = "/appointments/{id}")
    public ResponseEntity<Object> getAppointment(@PathVariable int id) {
        return ResponseEntity.ok(appointmentService.getAppointments(id));
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") int id) {
        appointmentService.deleteAppointment(id);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/appointments")
    public ResponseEntity<Object> addAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) {
        int newId = appointmentService.addAppointment(appointmentRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/appointments/{id}")
    public ResponseEntity<Object> updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
        appointmentService.updateAppointment(id, appointment);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/appointments/{id}")
    public ResponseEntity<Object> partialUpdateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
        appointmentService.partialUpdateAppointment(id, appointment);

        return ResponseEntity.noContent().build();
    }

}
