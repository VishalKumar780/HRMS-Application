package com.example.hrms.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // Constructor with only message
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with only cause
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    // Default constructor
    public ResourceNotFoundException() {
        super("Requested resource was not found.");
    }
}

