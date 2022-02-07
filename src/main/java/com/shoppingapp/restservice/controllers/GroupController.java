package com.shoppingapp.restservice.controllers;

import com.shoppingapp.restservice.models.Group;
import com.shoppingapp.restservice.models.repositories.IGroupRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class GroupController {
    private IGroupRepository groupRepository;

    GroupController(IGroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    @GetMapping("/groups")
    List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @GetMapping("/groups/{id}")
    Group getGroupById(@PathVariable Integer id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @PostMapping("/groups")
    Group createGroup(@RequestBody Group newGroup) {
        return groupRepository.save(newGroup);
    }

    @PutMapping("/groups/{id}")
    Group updateGroup(@RequestBody Group newGroup, @PathVariable Integer id) {
        return groupRepository.findById(id)
                .map(group -> {
                    group.setName(newGroup.getName());
                    group.setStatus(newGroup.getStatus());
                    return groupRepository.save(newGroup);
                })
                .orElseGet(() -> {
                    newGroup.setId(id);
                    return groupRepository.save(newGroup);
                });
    }

    @DeleteMapping("/groups/{id}")
    void deleteGroup(@PathVariable Integer id) {
        groupRepository.deleteById(id);
    }
}
