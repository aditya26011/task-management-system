package com.aditya.tutorial.advice;


import com.aditya.tutorial.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception){
        ApiError apiError=new ApiError();
        apiError.setMessage(exception.getMessage());
        apiError.setHttpStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
}
