package com.EventHorizon.EventHorizon.ExceptionHandling.UserExceptionHandling;


import com.EventHorizon.EventHorizon.ExceptionHandling.EventExceptionHandling.EventErrorResponse;
import com.EventHorizon.EventHorizon.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler
{
    @ExceptionHandler
    public ResponseEntity<EventErrorResponse> handleException(UserNotFoundException e)
    {
        EventErrorResponse error = new EventErrorResponse();
        error.status = HttpStatus.NOT_FOUND.value();
        error.message = "User Not Found";
        error.timestamp = System.currentTimeMillis();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

