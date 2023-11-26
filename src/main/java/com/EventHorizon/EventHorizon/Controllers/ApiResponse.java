package com.EventHorizon.EventHorizon.Controllers;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;


    public ApiResponse( String message, T data) {
        this.status= HttpStatus.OK;
        this.message = message;
        this.data = data;
    }


    public ApiResponse(String message) {
        this.status= HttpStatus.OK;
        this.message = message;
    }


}