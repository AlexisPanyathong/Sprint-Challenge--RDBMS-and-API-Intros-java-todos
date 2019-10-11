package com.lambdaschool.sprintjavatodo.service;

import com.lambdaschool.sprintjavatodo.model.Role;

import java.util.List;

public interface RoleService {

    // Finds all Role
    List<Role> findAll();

    // Find Role by is
    Role findRoleById(long id);

    void delete(long id);

    // Save Role
    Role save(Role role);

    Role findByName(String name);

    Role update(long id, Role role);

}
