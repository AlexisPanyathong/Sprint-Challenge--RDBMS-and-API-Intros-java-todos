package com.lambdaschool.sprintjavatodo.service;

import com.lambdaschool.sprintjavatodo.model.ToDo;

import java.util.List;

public interface ToDoService {

    List<ToDo> findAll();

    ToDo findToDoById(long id);

    List<ToDo> findByUserName(String username);

    void delete(long id);

    ToDo save(ToDo todo);

    ToDo update(ToDo todo, long id);
}
