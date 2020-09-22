package com.example.hotel_api;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/customer")
public class  CustomerController {

    @Autowired
    CustomerRepository cRepository;

    @GetMapping("/get-all-customers")
    public List<CustomerEntity> getAllCustomer() {
        List<CustomerEntity> allcustomerlist = cRepository.findAll();
        return allcustomerlist;

    }

    @GetMapping("/get-customer/{id}")
    public CustomerEntity getCustomerbyId(@PathVariable(value = "id") Integer customerId) {
        CustomerEntity customerEntity = cRepository.findById(customerId).get();

        return customerEntity;
    }

    @PostMapping("/create-customers")
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer) {

        CustomerEntity savedcustomer = cRepository.save(customer);

        return savedcustomer;
    }

    @PutMapping("/update-customer/{id}")
    public ResponseEntity<CustomerEntity> updateCustomer(@PathVariable(value = "id") Integer customerId,
                                                         @RequestBody CustomerEntity customerDetails) {
        CustomerEntity customer = cRepository.findById(customerId).get();

        customer.setHotelId(customerDetails.getHotelId());
        customer.setName(customerDetails.getName());
        customer.setLocation(customerDetails.getLocation());
        final CustomerEntity updatedEmployee = cRepository.save(customer);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete-customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Integer customerId) {
        CustomerEntity customer = cRepository.findById(customerId).get();

        cRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

