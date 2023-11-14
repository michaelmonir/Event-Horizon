package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class ParentException extends RuntimeException{
    String message;
    HttpStatus httpStatus;
}
