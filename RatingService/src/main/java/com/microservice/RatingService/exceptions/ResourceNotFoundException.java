package com.microservice.RatingService.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Rating not found on server");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
