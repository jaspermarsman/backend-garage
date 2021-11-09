package nl.marsman.garage.controller;

import nl.marsman.garage.model.Customer;
import nl.marsman.garage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value = "/klanten")
    public ResponseEntity<Object> getAllCustomers(){
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping(value = "klanten/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable int id) {
        return  ResponseEntity.ok(customerRepository.findById(id));
    }

//    @PostMapping(value = "customers")
//    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
//        long newId =
//    }
}
