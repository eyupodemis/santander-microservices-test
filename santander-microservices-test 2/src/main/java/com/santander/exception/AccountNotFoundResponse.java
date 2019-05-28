package com.santander.exception;

public class AccountNotFoundResponse extends ErrorResponse{

    public AccountNotFoundResponse(Long id) {
        super(1003, "Account not found with id: " + id);
    }

}
