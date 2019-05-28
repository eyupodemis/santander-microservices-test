package com.santander.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@SuppressWarnings("serial")
@ControllerAdvice
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerErrorMapper {

    //private static final long serialVersionUID = 1L;

    @Autowired
    ErrorResponse error ;

    @ExceptionHandler(ErrorResponse.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(ErrorResponse ex) {

        HttpStatus status;

        switch(ex.getErrorCode()) {
            case 1000 : status = HttpStatus.NOT_FOUND; break;
            case 1001 : status = HttpStatus.BAD_REQUEST; break;
            case 1003 : status = HttpStatus.NO_CONTENT; break;
            default   : status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity(ex,status);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception ex) {
        return new ResponseEntity(new ErrorResponse(1002,"Error occured!"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

}