package com.project.cruddemo.exceptions;

import com.project.cruddemo.models.Employee;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException (String message) {
        super(message);
    }

    public EmployeeNotFoundException (String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFoundException (Throwable cause) {
        super(cause);
    }
}
