package com.informatics.medical_record_spring.exception;

public class EntityNotFoundForDeletionException extends RuntimeException{

    public EntityNotFoundForDeletionException(String message){
        super(message);
    }
}
