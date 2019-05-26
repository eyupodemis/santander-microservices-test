package com.santander;

import com.santander.controller.AccountController;
import com.santander.dto.AccountDto;
import com.santander.entity.Account;
import com.santander.exception.AccountNotFoundResponse;
import com.santander.exception.ErrorResponse;
import com.santander.exception.InvalidAccountIdResponse;
import com.santander.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountTest {

    @Autowired
    private AccountController accountController;

    @Autowired
    private Account account;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ResponseEntity<Account> responseAccount;
    private ResponseEntity<Object>  responseObject;

    @Autowired
    private AccountServiceImpl accountServiceImpl;



    @Test
    public void testGetAccountByIdSuccess()  {
        ResponseEntity<AccountDto> accountDto =  accountController.getAccountById(1L);
        assertEquals(accountDto.getBody().getName(), "leonardo");
    }

    @Test(expected = InvalidAccountIdResponse.class)
    public void testGetAccountByIdWithInvalidId()  {
        ResponseEntity<AccountDto> accountDto =  accountController.getAccountById(-1L);
    }

    @Test(expected = AccountNotFoundResponse.class)
    public void testGetAccountByIdWithNonexistentAccount()  {
        ResponseEntity<AccountDto> accountDto =  accountController.getAccountById(5L);
    }

    @Test
    public void testGetAllAccountsSuccess()  {
        accountController.setAccountServiceImpl(accountServiceImpl);
        assertNotEquals(accountController.getAllAccounts().getBody().size(), 0);
    }

    @Test
    public void testGetAllAccountsNoRecordsFound()  {
        accountServiceImpl = mock(AccountServiceImpl.class);
        when(accountServiceImpl.getAllAccounts()).thenReturn(Collections.emptyList());
        accountController.setAccountServiceImpl(accountServiceImpl);
        ResponseEntity<List<AccountDto>> accountList = accountController.getAllAccounts();
        assertTrue(accountList.getBody().isEmpty());
    }

    @Test(expected = AccountNotFoundResponse.class)
    public void testUpdateAccountByIdWithNonexistentAccount() {

        account.setId(4L);
        account.setName("leonardo");
        account.setSurname("davinci");
        account.setAge(99);
        accountController.updateAccountById(account, account.getId());
    }

    @Test
    public void testUpdateAccountByIdSuccess()  {

        account.setId(3L);
        account.setName("michael");
        account.setSurname("jackson");
        account.setAge(99);
        responseAccount = accountController.updateAccountById(account, account.getId());
        assertEquals(responseAccount.getStatusCode().value(),200);

        ResponseEntity<AccountDto> accountDto =  accountController.getAccountById(account.getId());
        assertEquals(accountDto.getBody().getName(),account.getName());

    }

    @Test(expected = AccountNotFoundResponse.class)
    public void testDeleteAccountByIdNonexistingId()  {
        responseObject =  accountController.deleteAccountById(5L);
    }

    @Test
    public void testDeleteAccountByIdSuccess()  {
        responseObject =  accountController.deleteAccountById(3L);
        assertEquals("Deleting record",responseObject.getStatusCode().value(),200);

        /* Checking deleted record */
        exception.expect( AccountNotFoundResponse.class);
        ResponseEntity<AccountDto> accountDto =  accountController.getAccountById(3L);


    }

}
