package com.microservices.springsecurity;

import com.microservices.springsecurity.exceptions.EmployeeExistException;
import com.microservices.springsecurity.exceptions.EmployeeNotFoundException;
import com.microservices.springsecurity.model.Employee;
import com.microservices.springsecurity.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Employee API";
    }

    @GetMapping("/allemployees")
    public ResponseEntity<List<Employee>> getEmployees() {
        final Optional<List<Employee>> employees = employeeService.getEmployees();

        if (employees.isPresent()) {
            return new ResponseEntity<>(employees.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(employees.orElseThrow(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("empId") Integer empId)
            throws EmployeeNotFoundException {
        final Employee employee = employeeService.getEmployee(empId);

        return new ResponseEntity<>(employee, HttpStatus.FOUND);
    }

    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<Employee>> getEmployeeByFirstName(
            @PathVariable("FirstName") String firstName) {
        final Optional<List<Employee>> employees = employeeService.getEmployeesByFirstName(firstName);

        if (employees.isPresent()) {
            return new ResponseEntity<>(employees.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(employees.orElseThrow(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addemployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp)
            throws EmployeeExistException {
        return new ResponseEntity<>(employeeService.addEmployee(emp), HttpStatus.CREATED);
    }
}
