package com.kfh.assessment.education.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException{
    private String message;
    private String resourceName;

    public EmailAlreadyExistsException(String resourceName, String message){
        super(message);
        this.resourceName = resourceName;
    }
    public String getResourceName() {
        return resourceName;
    }
}