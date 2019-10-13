package com.lambdaschool.sprintjavatodo.service;

import com.lambdaschool.sprintjavatodo.model.Todo;
import java.util.List;

public interface TodoService
{
    Todo updateTodo(Todo todo, long todoid);
    List<Todo> findAllTodos();

    Todo save(Todo tempTodo);
}
