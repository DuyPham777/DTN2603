package com.example.buoi1.controller;

import com.example.buoi1.entity.Account;
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
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Account findById(@PathVariable Integer id) {
        return accountService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Account account) {
        accountService.create(account);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Integer id, @RequestBody Account account) {
        accountService.update(id, account);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Integer id) {
        accountService.deleteById(id);
    }
}
