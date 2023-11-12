package com.EventHorizon.EventHorizon.ExceptionHandling;

import com.EventHorizon.EventHorizon.ExceptionHandling.EventExceptionHandling.EventErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OtherExceptionsHandler
{
    @ExceptionHandler
    public ResponseEntity<EventErrorResponse> handleException(Exception e)
    {
        EventErrorResponse error = new EventErrorResponse();
        error.status = HttpStatus.BAD_REQUEST.value();
        error.message = "Wrong Event Format";
        error.timestamp = System.currentTimeMillis();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
