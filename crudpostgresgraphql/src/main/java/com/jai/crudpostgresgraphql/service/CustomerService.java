package com.jai.crudpostgresgraphql.service;

import com.jai.crudpostgresgraphql.dto.CustomerInputDto;
import com.jai.crudpostgresgraphql.entity.Customer;
import com.jai.crudpostgresgraphql.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(CustomerInputDto customerInputDto) {
        Customer customer = Customer.builder()
                .firstName(customerInputDto.firstName())
                .lastName(customerInputDto.lastName())
                .email(customerInputDto.email())
                .phoneNumber(customerInputDto.phoneNumber())
                .addressLine1(customerInputDto.addressLine1())
                .addressLine2(customerInputDto.addressLine2())
                .city(customerInputDto.city())
                .state(customerInputDto.state())
                .country(customerInputDto.country())
                .postCode(customerInputDto.postCode())
                .createdDate(new Date())
                .updatedDate(null)
                .build();

        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
