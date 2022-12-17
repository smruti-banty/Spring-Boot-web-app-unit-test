package com.example.demo1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(Exception.class)
    private ProblemDetail handleException(Exception exception)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,exception.getMessage());
    }
}
