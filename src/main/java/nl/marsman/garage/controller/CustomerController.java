package nl.marsman.garage.controller;

import nl.marsman.garage.dto.CustomerRequestDto;
import nl.marsman.garage.model.Car;
import nl.marsman.garage.model.Customer;
import nl.marsman.garage.repository.CustomerRepository;
import nl.marsman.garage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
public class CustomerController {

//    private CustomerService customerService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customers")
    public ResponseEntity<Object> getAllCustomers(@RequestParam(name="secondName", defaultValue = "") String secondName){
        return ResponseEntity.ok(customerService.getCustomers(secondName));
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable int id) {
        return  ResponseEntity.ok(customerService.getCustomer(id));
    }

    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        int newId = customerService.addCustomer(customerRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/customers/{id}")
    public ResponseEntity<Object> partialUpdateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        customerService.partialUpdateCustomer(id, customer);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/customers/{id}/cars")
    public ResponseEntity<Object> getCustomerCars(@PathVariable int id) {
        return  ResponseEntity.ok(customerService.getCustomerCars(id));
    }

    @PostMapping(value = "/customers/{id}/cars")
    public ResponseEntity<Object> addCustomerCar(@PathVariable int id, @RequestBody Car car) {
        customerService.addCustomerCars(id, car);
        return  ResponseEntity.created(null).build();
    }


}
