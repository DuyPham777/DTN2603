package com.example.buoi1.controller;

import com.example.buoi1.entity.Account;
import com.example.buoi1.dto.AccountDTO;
import com.example.buoi1.form.AccountForm;
import com.example.buoi1.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public List<AccountDTO> findAll() {
        return accountService.findAll();
    }

    @GetMapping(value = "/{id}")
    public AccountDTO findById(@PathVariable Integer id) {
        return accountService.findById(id);
    }

    @PostMapping
    public void createAccount(@RequestBody AccountForm form) {
        accountService.create(form);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Integer id, @RequestBody AccountForm form) {
        accountService.update(id, form);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Integer id) {
        accountService.deleteById(id);
    }
}
