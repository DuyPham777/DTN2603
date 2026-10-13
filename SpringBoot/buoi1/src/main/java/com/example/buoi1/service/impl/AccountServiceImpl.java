package com.example.buoi1.service.impl;

import com.example.buoi1.entity.Account;
import com.example.buoi1.repository.IAccountRepository;
import com.example.buoi1.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void update(Integer id, Account accountUpdate) {
        // tìm account theo id muốn update
        Account acc = accountRepository.findById(id).orElse(null);
        if (Objects.isNull(acc)) {
            throw new RuntimeException("ID ko tồn tại!");
        }

        acc.setEmail(accountUpdate.getEmail());
        acc.setUsername(accountUpdate.getUsername());
        acc.setFullName(accountUpdate.getFullName());
        acc.setGender(accountUpdate.getGender());
        acc.setDepartment(accountUpdate.getDepartment());
        acc.setPosition(accountUpdate.getPosition());
        accountRepository.save(acc);
    }

    @Override
    public void deleteById(Integer id) {
        // nếu id không tồn tại thì báo lỗi
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("ID ko tồn tại!");
        }
        accountRepository.deleteById(id);
    }
}
