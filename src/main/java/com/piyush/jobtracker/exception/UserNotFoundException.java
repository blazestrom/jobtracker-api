package com.piyush.jobtracker.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class UserNotFoundException extends  RuntimeException {

    public UserNotFoundException (String message){
        super(message);
    }

}
