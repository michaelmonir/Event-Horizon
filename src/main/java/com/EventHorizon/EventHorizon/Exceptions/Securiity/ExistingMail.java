package com.EventHorizon.EventHorizon.Exceptions.Securiity;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;



public class ExistingMail extends BaseException {

        public ExistingMail() {
            this.message="Mail Already Exists";
            this.httpStatus= HttpStatus.BAD_REQUEST;

        }
}