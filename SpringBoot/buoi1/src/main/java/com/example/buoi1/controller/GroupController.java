package com.example.buoi1.controller;

import com.example.buoi1.entity.Group;
import com.example.buoi1.service.IGroupService;
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
@RequestMapping(value = "/groups")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @GetMapping
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Group findById(@PathVariable Integer id) {
        return groupService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Group group) {
        groupService.create(group);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Integer id, @RequestBody Group group) {
        groupService.update(id, group);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Integer id) {
        groupService.deleteById(id);
    }
}
