package com.santander.controller;

import com.santander.dto.AccountDto;
import com.santander.entity.Account;
import com.santander.exception.ErrorResponse;
import com.santander.exception.InvalidAccountIdResponse;
import com.santander.service.AccountServiceImpl;
import com.santander.service.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private  AccountServiceImpl accountServiceImpl;

    @Autowired
    private AccountValidator validator;

    public void setAccountServiceImpl(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    @GetMapping("/accounts/{id}")
    @ResponseBody
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {

        validator.validate(id);
        return new ResponseEntity(accountServiceImpl.getAccountById(id), HttpStatus.OK);
    }


    @GetMapping("/accounts")
    @ResponseBody
    public ResponseEntity<List<AccountDto>> getAllAccounts()  {

        List<AccountDto> accountList = accountServiceImpl.getAllAccounts();
        return new ResponseEntity(accountList,HttpStatus.OK);

    }


    @PutMapping("/accounts/{id}")
    @ResponseBody
    public ResponseEntity updateAccountById(@RequestBody Account account,@PathVariable Long id)  {

        validator.validate(id);
        Account updatedAccount = accountServiceImpl.updateAccountById(account);

        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/accounts/{id}")
    @ResponseBody
    public ResponseEntity deleteAccountById(@PathVariable Long id)  {

        validator.validate(id);
        accountServiceImpl.deleteAccountById(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
