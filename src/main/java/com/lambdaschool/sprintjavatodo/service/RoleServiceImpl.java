package com.lambdaschool.sprintjavatodo.service;

import com.lambdaschool.sprintjavatodo.model.Role;
import com.lambdaschool.sprintjavatodo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository rolerepos;

    // Find All
    @Override
    public List<Role> findAll() {

        List<Role> list = new ArrayList<>();
        rolerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    // Find Role by id
    @Override
    public Role findRoleById(long id) {

        return rolerepos.findAllById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

    }

    // Delete
    @Override
    public void delete(long id) {
        rolerepos.findAllById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        rolerepos.deleteById(id);

    }

    // Save
    @Transactional
    @Override
    public Role save(Role role) {

        return rolerepos.save(role);
    }
}
