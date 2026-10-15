package com.example.buoi1.service;

import com.example.buoi1.dto.GroupDTO;
import com.example.buoi1.entity.Account;
import com.example.buoi1.dto.AccountDTO;
import com.example.buoi1.form.AccountForm;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> findAll();

    AccountDTO findById(Integer id);

    void create(AccountForm form);

    void update(Integer id, AccountForm form);

    void deleteById(Integer id);

}
