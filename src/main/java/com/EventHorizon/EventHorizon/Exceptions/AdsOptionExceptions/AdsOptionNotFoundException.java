package com.EventHorizon.EventHorizon.Exceptions.AdsOptionExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class AdsOptionNotFoundException extends BaseException
{
    public AdsOptionNotFoundException()
    {
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.message = "Ads Option not found";
    }
}
