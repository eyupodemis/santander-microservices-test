package com.santander.entity;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public class AccountDto {

    private final String name;
    private final String surname;
    private final int age;

    public AccountDto(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

}
