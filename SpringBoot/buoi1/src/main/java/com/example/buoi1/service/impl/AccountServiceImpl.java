package com.example.buoi1.service.impl;

import com.example.buoi1.dto.GroupDTO;
import com.example.buoi1.entity.Account;
import com.example.buoi1.dto.AccountDTO;
import com.example.buoi1.entity.GroupAccount;
import com.example.buoi1.entity.Position;
import com.example.buoi1.form.AccountForm;
import com.example.buoi1.entity.Department;
import com.example.buoi1.repository.IAccountRepository;
import com.example.buoi1.repository.IDepartmentRepository;
import com.example.buoi1.repository.IGroupAccountRepository;
import com.example.buoi1.repository.IPositionRepository;
import com.example.buoi1.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;
    private IDepartmentRepository departmentRepository;
    private IPositionRepository positionRepository;
    private IGroupAccountRepository groupAccountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AccountDTO> findAll() {
        List<Account> accounts = accountRepository.findAll();

        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account : accounts) {
            AccountDTO dto = modelMapper.map(account, AccountDTO.class);
            accountDTOS.add(dto);
        }
        return accountDTOS;
    }

    @Override
    public AccountDTO findById(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        if(Objects.isNull(account)){
            throw new RuntimeException("ID not found");
        }
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public void create(AccountForm form) {
        if (accountRepository.existsByUsername(form.getUsername())) {
            throw new RuntimeException("Username đã tồn tại!");
        }
        if (accountRepository.existsByEmail(form.getEmail())) {
            throw new RuntimeException("Email đã tồn tại!");
        }
        Account account = new Account();
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullname());
        account.setEmail(form.getEmail());
        account.setPassword(form.getPassword());
        account.setGender(form.getGender());

        Department department = departmentRepository.findById(form.getDepartmentId()).orElse(null);
        if (Objects.isNull(department)) {
            throw new RuntimeException("ID ko tồn tại!");
        }
        account.setDepartment(department);

        Position position = positionRepository.findById(form.getPositionId()).orElse(null);
        if (Objects.isNull(position)) {
            throw new RuntimeException("Position ID ko tồn tại!");
        }
        account.setPosition(position);
        accountRepository.save(account);
    }

    @Override
    public void update(Integer id, AccountForm form) {
        Account account = accountRepository.findById(id).orElse(null);
        if (Objects.isNull(account)) {
            throw new RuntimeException("ID ko tồn tại!");
        }

        if (accountRepository.existsByUsernameAndIdNot(form.getUsername(), id)) {
            throw new RuntimeException("Username đã tồn tại!");
        }
        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), id)) {
            throw new RuntimeException("Email đã tồn tại!");
        }

        Department department = departmentRepository.findById(form.getDepartmentId()).orElse(null);
        if (Objects.isNull(department)) {
            throw new RuntimeException("Department ID ko tồn tại!");
        }

        Position position = positionRepository.findById(form.getPositionId()).orElse(null);
        if (Objects.isNull(position)) {
            throw new RuntimeException("Position ID ko tồn tại!");
        }

        account.setEmail(form.getEmail());
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullname());
        account.setGender(form.getGender());
        account.setDepartment(department);
        account.setPosition(position);

        if (Objects.nonNull(form.getPassword()) && !form.getPassword().isBlank()) {
            account.setPassword(form.getPassword());
        }
        accountRepository.save(account);
    }

    @Override
    public void deleteById(Integer id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("ID ko tồn tại!");
        }
        accountRepository.deleteById(id);
    }




}
