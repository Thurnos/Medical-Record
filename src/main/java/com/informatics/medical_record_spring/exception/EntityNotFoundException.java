package com.informatics.medical_record_spring.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message){
        super(message);
    }

}
