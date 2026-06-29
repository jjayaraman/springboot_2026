package com.jai.springgraphql.repository;

import com.jai.springgraphql.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class EmployeeRepository {

    // Mock data
    private final List<Employee> employees = new ArrayList<>(List.of(
            new Employee("1", "Jay", "Kumar", "j.k@gmail.com", "1"),
            new Employee("2", "John", "Doe", "john.doe@gmail.com", "2")
    ));

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(String id) {
        return employees.stream().filter(employee -> employee.id().equals(id)).findFirst().orElseThrow();
    }

    public Employee create(String firstName, String lastName, String email, String departmentId) {
        Employee employee = new Employee(UUID.randomUUID().toString(), firstName, lastName, email, departmentId);
        employees.add(employee);
        return employee;
    }

    public Employee update(String id, String firstName, String lastName, String email, String departmentId) {

        delete(id);
        Employee employee = new Employee(id, firstName, lastName, email, departmentId);
        employees.add(employee);
        return employee;
    }

    public Employee delete(String id) {
        Employee e = employees.stream().filter(employee1 -> employee1.id().equals(id)).findFirst().orElseThrow();
        employees.remove(e);
        return e;
    }
}
