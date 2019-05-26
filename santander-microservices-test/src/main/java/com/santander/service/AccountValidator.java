package com.santander.service;

import com.santander.exception.InvalidAccountIdResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountValidator {

    private boolean isValid(long accountId) {

        if(accountId < 1)
            return false;

        return true;

    }

    public void validate(long accountId){
        Optional.ofNullable(accountId).filter(this::isValid).orElseThrow(()-> new InvalidAccountIdResponse(accountId) );
    }


}
