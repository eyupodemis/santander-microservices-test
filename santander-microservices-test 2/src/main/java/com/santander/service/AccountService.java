package com.santander.service;

import com.santander.dto.AccountDto;
import com.santander.exception.ErrorResponse;
import com.santander.dto.AccountDto;

import java.io.IOException;
import java.util.List;
import com.santander.entity.Account;

public interface AccountService {

    public AccountDto getAccountById(Long id);
    public List<AccountDto> getAllAccounts();
    public Account updateAccountById(Account account);
    public void deleteAccountById(Long id) ;

}
