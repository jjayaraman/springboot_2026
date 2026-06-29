package com.jai.crudpostgresgraphql.service;

import com.jai.crudpostgresgraphql.dto.CustomerInputDto;
import com.jai.crudpostgresgraphql.entity.Customer;
import com.jai.crudpostgresgraphql.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public List<Customer> getCustomers() {
        return customerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Customer createCustomer(CustomerInputDto customerInputDto) {

        Customer customer = Customer.builder().build();

        modelMapper.map(customerInputDto, customer);
        customer.setId(null);
        customer.setCreatedDate(new Date());

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, CustomerInputDto customerInputDto) {
        log.debug("Updating customer with id {} and input data {}", id, customerInputDto);
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " does not exist"));

        modelMapper.map(customerInputDto, existingCustomer);
        existingCustomer.setUpdatedDate(new Date());

        log.debug("Updating customer with id {} and combined data {}", id, existingCustomer.toString());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
