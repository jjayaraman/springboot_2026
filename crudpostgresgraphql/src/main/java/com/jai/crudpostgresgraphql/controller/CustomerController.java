package com.jai.crudpostgresgraphql.controller;

import com.jai.crudpostgresgraphql.dto.CustomerInputDto;
import com.jai.crudpostgresgraphql.entity.Customer;
import com.jai.crudpostgresgraphql.service.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @QueryMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @MutationMapping
    public Customer createCustomer(@Argument CustomerInputDto input) {
        return customerService.createCustomer(input);
    }
}
