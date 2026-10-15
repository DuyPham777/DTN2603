package com.example.buoi1.service;

import com.example.buoi1.dto.AccountDTO;
import com.example.buoi1.dto.GroupDTO;
import com.example.buoi1.form.GroupAccountForm;
import com.example.buoi1.form.GroupForm;

import java.util.List;

public interface IGroupService {
    List<GroupDTO> findAll();

    GroupDTO findById(Integer id);

    void create(GroupForm form);

    void update(Integer id, GroupForm form);

    void deleteById(Integer id);

    void addAccountToGroup(GroupAccountForm form);

}
