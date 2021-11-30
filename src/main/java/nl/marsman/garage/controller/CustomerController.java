package nl.marsman.garage.controller;

import nl.marsman.garage.model.Customer;
import nl.marsman.garage.repository.CustomerRepository;
import nl.marsman.garage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        int newId = customerService.addCustomer(customer);

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


}
