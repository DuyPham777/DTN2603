package com.example.buoi1.service;

import com.example.buoi1.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    Account findById(Integer id);

    void create(Account account);

    void update(Integer id, Account account);

    void deleteById(Integer id);
}
