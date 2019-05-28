package com.santander.exception;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class ErrorResponse extends RuntimeException {

    private int errorCode;
    private String message;

    public ErrorResponse() {}

    public ErrorResponse(ErrorResponse e) {
        this.errorCode = e.errorCode;
        this.message = e.message;
    }

    public ErrorResponse(int errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
