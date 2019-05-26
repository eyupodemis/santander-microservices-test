package com.santander.exception;

public class InvalidAccountIdResponse extends ErrorResponse {
    public InvalidAccountIdResponse(Long id) {
        super(1001, "Invalid id: " + id);
    }
}
