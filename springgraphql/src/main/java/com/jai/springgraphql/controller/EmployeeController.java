package com.jai.springgraphql.controller;

import com.jai.springgraphql.model.Department;
import com.jai.springgraphql.model.Employee;
import com.jai.springgraphql.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

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
    public Employee create(@Argument String firstName, @Argument String lastName, @Argument String email, @Argument String departmentId) {
        return employeeRepository.create(firstName, lastName, email, departmentId);
    }

    @MutationMapping
    public Employee update(@Argument String id, @Argument String firstName, @Argument String lastName, @Argument String email, @Argument String departmentId) {
        return employeeRepository.update(id, firstName, lastName, email, departmentId);
    }

    @MutationMapping
    public Employee deleteEmployee(@Argument String id) {
        return employeeRepository.delete(id);
    }


    @BatchMapping(field = "department")
    public Map<Employee, Department> department(List<Employee> employees) {

        logger.debug("Batch employees ");

        // Mock department data
        final Map<String, Department> departments =
                Map.of("1", new Department("1", "IT"),
                        "2", new Department("2", "HR"));

        return employees.stream().collect(Collectors.toMap(
                employee -> employee,
                employee -> departments.getOrDefault(employee.departmentId(), new Department("unknown", "unknown"))
        ));
    }
}
