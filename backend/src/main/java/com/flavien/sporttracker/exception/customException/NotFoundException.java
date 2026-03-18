package com.flavien.sporttracker.exception.customException;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

}
