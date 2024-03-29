package nl.marsman.garage.service;

import nl.marsman.garage.dto.CustomerRequestDto;
import nl.marsman.garage.exception.RecordNotFoundException;
import nl.marsman.garage.model.Car;
import nl.marsman.garage.model.Customer;
import nl.marsman.garage.repository.CarRepository;
import nl.marsman.garage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    public Iterable<Customer> getCustomers(String secondName) {
        if (secondName.isEmpty()) {
            return customerRepository.findAll();
        }
        else {
            return customerRepository.findAllBySecondNameContainingIgnoreCase(secondName);
        }
    }


    public Customer getCustomer(int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }

    }

    public void deleteCustomer(int id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public int addCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = new Customer();
        customer.setFirstName(customerRequestDto.getFirstName());
        customer.setSecondName(customerRequestDto.getSecondName());

        Customer newCustomer = customerRepository.save(customer);
        return newCustomer.getId();
    }

    public void updateCustomer(int id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer storedCustomer = optionalCustomer.get();

            customer.setId(storedCustomer.getId());
            customerRepository.save(customer);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void partialUpdateCustomer(int id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer storedCustomer = customerRepository.findById(id).orElse(null);

            if (customer.getFirstName()!=null && !customer.getFirstName().isEmpty()) {
                storedCustomer.setFirstName(customer.getFirstName());
            }
            if (customer.getSecondName()!=null && !customer.getSecondName().isEmpty()) {
                storedCustomer.setSecondName(customer.getSecondName());
            }

            customerRepository.save(storedCustomer);

        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public List<Car> getCustomerCars(int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return customer.getCars();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }

    public void  addCustomerCars(int id, Car car) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            List<Car> cars = customer.getCars();

            carRepository.save(car);

            cars.add(car);
            customerRepository.save(customer);

        }
        else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }



}
