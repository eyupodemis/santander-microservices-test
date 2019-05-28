package com.santander.entity;

import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Component
@Entity
@Table(name="account")
public class Account {

    @GeneratedValue
    @Id
    private Long id;

    @Column
    @Size(max=50)
    private String name;

    @Column
    @Size(max=50)
    private String surname;

    private int age;

    public Account(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
