package com.prince.hotelService.exceptionHandling;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super("Resource not found !!");
    }
    public ResourceNotFoundException() {
        super("Resource not found !!");
    }

}
