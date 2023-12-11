package com.EventHorizon.EventHorizon.Exceptions.PagingExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidPageIndexException extends BaseException {
    public InvalidPageIndexException() {
        this.message="Invalid Page Index";
        this.httpStatus= HttpStatus.BAD_REQUEST;
    }
}
