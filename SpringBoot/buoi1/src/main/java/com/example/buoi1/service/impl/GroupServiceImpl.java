package com.example.buoi1.service.impl;

import com.example.buoi1.entity.Group;
import com.example.buoi1.repository.IGroupRepository;
import com.example.buoi1.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group findById(Integer id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void update(Integer id, Group groupUpdate) {
        Group group = groupRepository.findById(id).orElse(null);
        if (Objects.isNull(group)) {
            throw new RuntimeException("ID ko tồn tại!");
        }

        group.setGroupName(groupUpdate.getGroupName());
        group.setCreatorId(groupUpdate.getCreatorId());
        groupRepository.save(group);
    }

    @Override
    public void deleteById(Integer id) {
        // nếu id không tồn tại thì báo lỗi
        if (!groupRepository.existsById(id)) {
            throw new RuntimeException("ID ko tồn tại!");
        }
        groupRepository.deleteById(id);
    }
}
