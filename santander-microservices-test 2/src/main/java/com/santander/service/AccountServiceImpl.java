package com.santander.service;

import com.santander.dto.AccountDto;
import com.santander.exception.AccountNotFoundResponse;
import com.santander.exception.ErrorResponse;
import com.santander.repository.AccountRepository;
import com.santander.service.AccountService;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santander.entity.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto getAccountById(Long id) {

        Account accountResult =
                accountRepository.findById(id)
                        .orElseThrow(() -> new AccountNotFoundResponse(id));

        return createAccountDto(accountResult);

    }

    @Override
    public List<AccountDto> getAllAccounts() {

        List<AccountDto> accountDtoList = new ArrayList<>();
        List<Account> accountList = Lists.newArrayList(accountRepository.findAll());

        return accountList.stream()
                .map(this::createAccountDto)
                .collect(Collectors.toList());

    }

    @Override
    public Account updateAccountById(Account account) {

        Account accountResult = accountRepository
                .findById(account.getId())
                .orElseThrow(() -> new AccountNotFoundResponse(account.getId()));

        Account updatedAccount = accountRepository.save(account);
        return updatedAccount;

    }

    @Override
    public void deleteAccountById(Long id) {

        Account accountResult = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundResponse(id));

        accountRepository.deleteById(accountResult.getId());
    }

    private AccountDto createAccountDto(Account account) {
        return new AccountDto(account.getName(), account.getSurname(), account.getAge());
    }


}


