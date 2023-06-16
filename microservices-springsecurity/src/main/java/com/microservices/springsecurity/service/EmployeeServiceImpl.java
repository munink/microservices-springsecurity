package com.microservices.springsecurity.service;

import com.microservices.springsecurity.exceptions.EmployeeExistException;
import com.microservices.springsecurity.exceptions.EmployeeNotFoundException;
import com.microservices.springsecurity.model.Employee;
import com.microservices.springsecurity.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee getEmployee(int empId) throws EmployeeNotFoundException {
        final Optional<Employee> employee = this.employeeRepository.findById(empId);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException("Contact not found");
        }
    }

    @Override
    public Employee addEmployee(Employee emp) throws EmployeeExistException {
        if (this.employeeRepository.existsById(emp.getId())) {
            throw new EmployeeExistException("Employee already exist!");
        } else {
            return this.employeeRepository.save(emp);
        }
    }

    @Override
    public Optional<List<Employee>> getEmployees() {
        final List<Employee> employees = this.employeeRepository.findAll();

        if (employees.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(employees);
    }

    @Override
    public Optional<List<Employee>> getEmployeesByFirstName(String firstName) {
        return this.employeeRepository.findByFirstName(firstName);
    }
}
