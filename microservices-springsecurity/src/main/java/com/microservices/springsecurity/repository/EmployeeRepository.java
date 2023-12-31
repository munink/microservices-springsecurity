package com.microservices.springsecurity.repository;

import com.microservices.springsecurity.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<List<Employee>> findByFirstName(String firstName);
}
