package com.EventHorizon.EventHorizon.ExceptionHandling;


import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler
{
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BaseException e)
    {
        ErrorResponse error = new ErrorResponse();
        error.status = e.httpStatus.value();
        error.message = e.message;
        error.timestamp = System.currentTimeMillis();

        return new ResponseEntity<>(error, e.httpStatus);
    }
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleException(Exception e)
//    {
//        ErrorResponse error = new ErrorResponse();
//        error.status = HttpStatus.BAD_REQUEST.value();
//        error.message = "Unexpected Error";
//        error.timestamp = System.currentTimeMillis();
//
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
}