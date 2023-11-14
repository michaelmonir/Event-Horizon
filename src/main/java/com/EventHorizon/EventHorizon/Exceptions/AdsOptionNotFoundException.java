package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class AdsOptionNotFoundException extends BaseException
{
    public AdsOptionNotFoundException()
    {
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.message = "Ads Option not found";
    }
}
