package com.EventHorizon.EventHorizon.Exceptions.Securiity;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;

public class ExistingUserName extends BaseException {
    public ExistingUserName() {
        this.httpStatus = org.springframework.http.HttpStatus.BAD_REQUEST;
        this.message = "Username already exists";
    }

}