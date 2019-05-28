package com.santander.repository;


import org.springframework.data.repository.CrudRepository;
import com.santander.entity.Account;


public interface AccountRepository extends CrudRepository<Account, Long> {


}
