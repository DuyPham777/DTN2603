package com.example.buoi1.service;

import com.example.buoi1.entity.Group;

import java.util.List;

public interface IGroupService {
    List<Group> findAll();

    Group findById(Integer id);

    void create(Group group);

    void update(Integer id, Group group);

    void deleteById(Integer id);
}
