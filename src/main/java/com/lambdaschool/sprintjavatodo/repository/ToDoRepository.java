package com.lambdaschool.sprintjavatodo.repository;

import com.lambdaschool.sprintjavatodo.model.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, Long> {

}
