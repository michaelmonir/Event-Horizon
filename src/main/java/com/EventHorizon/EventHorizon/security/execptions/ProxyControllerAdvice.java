package com.EventHorizon.EventHorizon.security.execptions;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProxyControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ProxyErrorResponse> inValidJwt(ForbiddenException s){
        ProxyErrorResponse studentErrorResponse=new ProxyErrorResponse().builder().
                status(HttpStatus.BAD_REQUEST.value()).
                message(s.getMessage()).
                timeStamp(System.currentTimeMillis()).
                build();
        return new ResponseEntity<>(studentErrorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ProxyErrorResponse> existingMail(ExistingMail s){
        ProxyErrorResponse studentErrorResponse=new ProxyErrorResponse().builder().
                status(HttpStatus.BAD_REQUEST.value()).
                message(s.getMessage()).
                timeStamp(System.currentTimeMillis()).
                build();
        return new ResponseEntity<>(studentErrorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ProxyErrorResponse> existingUserName(ExistingUserName s){
        ProxyErrorResponse studentErrorResponse=new ProxyErrorResponse().builder().
                status(HttpStatus.NOT_MODIFIED.value()).
                message(s.getMessage()).
                timeStamp(System.currentTimeMillis()).
                build();
        return new ResponseEntity<>(studentErrorResponse,HttpStatus.NOT_MODIFIED);
    }
}
