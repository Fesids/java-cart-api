package com.basejava.cart.exceptions.handler;

import com.basejava.cart.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UserTrackerGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException userNotFoundException, WebRequest request){
        return super.handleExceptionInternal(userNotFoundException, userNotFoundException.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }

}
