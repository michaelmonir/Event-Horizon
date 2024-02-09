package com.EventHorizon.EventHorizon.Exceptions.Filter;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundViewRepositoryService extends BaseException {

    public NotFoundViewRepositoryService() {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "View Repository Service not found";
    }
}
