package com.santander.service;

import com.santander.entity.AccountDto;
import java.util.List;
import com.santander.entity.Account;

public interface AccountService {

    public AccountDto getAccountById(Long id);
    public List<AccountDto> getAllAccounts();
    public Account updateAccountById(Account account);
    public void deleteAccountById(Long id) ;

}
