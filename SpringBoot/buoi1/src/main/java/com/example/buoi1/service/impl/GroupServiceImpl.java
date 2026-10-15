package com.example.buoi1.service.impl;

import com.example.buoi1.dto.AccountDTO;
import com.example.buoi1.dto.GroupDTO;
import com.example.buoi1.entity.Account;
import com.example.buoi1.entity.Group;
import com.example.buoi1.entity.GroupAccount;
import com.example.buoi1.form.GroupAccountForm;
import com.example.buoi1.form.GroupForm;
import com.example.buoi1.repository.IAccountRepository;
import com.example.buoi1.repository.IGroupAccountRepository;
import com.example.buoi1.repository.IGroupRepository;
import com.example.buoi1.service.IGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private IGroupRepository groupRepository;
    private IAccountRepository accountRepository;
    private IGroupAccountRepository groupAccountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<GroupDTO> findAll() {
        List<Group> groups = groupRepository.findAll();
        List<GroupDTO> groupDTOS = new ArrayList<>();
        for (Group group : groups) {
            GroupDTO dto = modelMapper.map(group, GroupDTO.class);
            groupDTOS.add(dto);
        }
        return groupDTOS;
    }

    @Override
    public GroupDTO findById(Integer id) {
        Group group = groupRepository.findById(id).orElse(null);
        if (Objects.isNull(group)) {
            throw new RuntimeException("ID ko tồn tại!");
        }
        return modelMapper.map(group, GroupDTO.class);
    }

    @Override
    public void create(GroupForm form) {
        if (groupRepository.existsByGroupName(form.getGroupName())) {
            throw new RuntimeException("Group name đã tồn tại!");
        }
        Account creator = accountRepository.findById(form.getCreatorId()).orElse(null);
        if (Objects.isNull(creator)) {
            throw new RuntimeException("Creator ID ko tồn tại!");
        }

        Group group = new Group();
        group.setGroupName(form.getGroupName());
        group.setCreatorId(creator);
        groupRepository.save(group);
    }

    @Override
    public void update(Integer id, GroupForm form) {
        Group group = groupRepository.findById(id).orElse(null);
        if (Objects.isNull(group)) {
            throw new RuntimeException("ID ko tồn tại!");
        }
        // validate: group name không trùng với group khác
        if (groupRepository.existsByGroupNameAndGroupIdNot(form.getGroupName(), id)) {
            throw new RuntimeException("Group name đã tồn tại!");
        }
        Account creator = accountRepository.findById(form.getCreatorId()).orElse(null);
        if (Objects.isNull(creator)) {
            throw new RuntimeException("Creator ID ko tồn tại!");
        }
    }

    @Override
    public void deleteById (Integer id){
        if (!groupRepository.existsById(id)) {
            throw new RuntimeException("ID ko tồn tại!");
        }
        groupAccountRepository.deleteAll(groupAccountRepository.findByGroup_GroupId(id));
        groupRepository.deleteById(id);
    }

    @Override
    public void addAccountToGroup(GroupAccountForm form) {
        Group group = groupRepository.findById(form.getGroupId()).orElse(null);
        if (Objects.isNull(group)) {
            throw new RuntimeException("Group ID ko tồn tại!");
        }
        Account account = accountRepository.findById(form.getAccountId()).orElse(null);
        if (Objects.isNull(account)) {
            throw new RuntimeException("Account ID ko tồn tại!");
        }

        // validate: account đã có trong group thì không thêm nữa
        GroupAccount.Key key = new GroupAccount.Key(group.getGroupId(), account.getId());
        if (groupAccountRepository.existsById(key)) {
            throw new RuntimeException("Account đã có trong group!");
        }

        GroupAccount groupAccount = new GroupAccount();
        groupAccount.setId(key);
        groupAccount.setGroup(group);
        groupAccount.setAccount(account);
        groupAccountRepository.save(groupAccount);
    }

}
