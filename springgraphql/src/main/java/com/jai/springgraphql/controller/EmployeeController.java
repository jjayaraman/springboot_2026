package com.jai.springgraphql.controller;

import com.jai.springgraphql.model.Employee;
import com.jai.springgraphql.repository.EmployeeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeeController {

    EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @QueryMapping
    public List<Employee> employees() {
        return employeeRepository.findAll();
    }

    @QueryMapping
    public Employee findById(@Argument String id) {
        return employeeRepository.findById(id);
    }

    @MutationMapping
    public Employee create(@Argument String firstName, @Argument String lastName, @Argument String email) {
        return employeeRepository.create(firstName, lastName, email);
    }

    @MutationMapping
    public Employee update(@Argument String id, @Argument String firstName, @Argument String lastName, @Argument String email) {
        return employeeRepository.update(id, firstName, lastName, email);
    }

    @MutationMapping
    public Employee deleteEmployee(@Argument String id) {
        return employeeRepository.delete(id);
    }


}
