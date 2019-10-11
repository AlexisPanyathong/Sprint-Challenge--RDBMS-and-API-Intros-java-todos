package com.lambdaschool.sprintjavatodo.repository;

import com.lambdaschool.sprintjavatodo.model.User;
import org.springframework.data.repository.CrudRepository;

// All Repositories are Interface
public interface UserRespository extends CrudRepository<User, Long> {

    User findByUserName(String username);
}
