package com.lambdaschool.sprintjavatodo.repository;

import com.lambdaschool.sprintjavatodo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// All Repositories are Interface
public interface UserRespository extends CrudRepository<User, Long> {


    List<User> findByUsernameContainingIgnoreCase(String name);

    User findByUsername(String username);
}
