package com.EventHorizon.EventHorizon.Exceptions.PagingExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidPageSize extends BaseException {


    public InvalidPageSize() {
        this.message="Invalid Page Size";
        this.httpStatus= HttpStatus.BAD_REQUEST;
    }
}
