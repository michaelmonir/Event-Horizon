package com.EventHorizon.EventHorizon.ExceptionHandling.EventExceptionHandling;


import com.EventHorizon.EventHorizon.Exceptions.*;
import com.EventHorizon.EventHorizon.Exceptions.NotOrganizerOfThisEventException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class EventRestExceptionHandler
{
    @ExceptionHandler
    public ResponseEntity<EventErrorResponse> handleException(BaseException e)
    {
        EventErrorResponse error = new EventErrorResponse();
        error.status = e.httpStatus.value();
        error.message = e.message;
        error.timestamp = System.currentTimeMillis();

        return new ResponseEntity<>(error, e.httpStatus);
    }

    @ExceptionHandler
    public ResponseEntity<EventErrorResponse> handleException(NotOrganizerOfThisEventException e)
    {
        EventErrorResponse error = new EventErrorResponse();
        error.status = HttpStatus.NOT_FOUND.value();
        error.message = "Event Already Existing";
        error.timestamp = System.currentTimeMillis();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
