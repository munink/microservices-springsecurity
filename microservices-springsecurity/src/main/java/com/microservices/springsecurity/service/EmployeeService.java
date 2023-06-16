package com.microservices.springsecurity.service;

import com.microservices.springsecurity.exceptions.EmployeeExistException;
import com.microservices.springsecurity.exceptions.EmployeeNotFoundException;
import com.microservices.springsecurity.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee getEmployee(int empId) throws EmployeeNotFoundException;

    Employee addEmployee(Employee emp) throws EmployeeExistException;

    Optional<List<Employee>> getEmployees();

    Optional<List<Employee>> getEmployeesByFirstName(String firstName);
}
