package com.microservices.springsecurity.exceptions;

public class EmployeeExistException extends Exception {

    public EmployeeExistException(String message) {
        super(message);
    }
}
