package com.prince.rating.RatingService.Exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super("User is not found !!");
    }
}
