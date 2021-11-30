package nl.marsman.garage.service;

import nl.marsman.garage.exception.RecordNotFoundException;
import nl.marsman.garage.model.Customer;
import nl.marsman.garage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

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

    public int addCustomer(Customer customer) {
//        String isbn = customer.getIsbn();
//        List<Book> books = (List<Book>)bookRepository.findAllByIsbn(isbn);
//        if (books.size() > 0) {
//            throw new BadRequestException("Isbn already exists!!!");
//        }

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

            customerRepository.save(customer);

        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }



}
