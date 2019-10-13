package com.lambdaschool.sprintjavatodo.service;

import com.lambdaschool.sprintjavatodo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

//    User findUserByName(String name);

    User findUserById(long id);

    void delete(long id);

    User save(User user);

    User update(User user, long id, boolean isAdmin);

    List<User> findByNameContaining(String username);

    User findByName(String name);

    void deleteUserRole(long userid, long roleid);

    void addUserRole(long userid, long roleid);

    User findUserByName(String name);
}
