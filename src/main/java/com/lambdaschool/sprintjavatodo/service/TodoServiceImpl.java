package com.lambdaschool.sprintjavatodo.service;

import com.lambdaschool.sprintjavatodo.model.Todo;
import com.lambdaschool.sprintjavatodo.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodosRepository todorepos;
    @Transactional
    @Override
    public Todo updateTodo(Todo todo, long todoid)
    {
        Todo currentTodo = todorepos.findById(todoid).orElseThrow(() -> new EntityNotFoundException(Long.toString(todoid)));
        if (todo.getDatestarted() != null)
        {
            currentTodo.setDatestarted(todo.getDatestarted());
        }
        if (todo.getDescription() != null)
        {
            currentTodo.setDescription(todo.getDescription());
        }
        currentTodo.setCompleted(todo.isCompleted());
        return todorepos.save(currentTodo);
    }
    @Override
    public List<Todo> findAllTodos()
    {
        List<Todo> rtnList = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Todo save(Todo tempTodo) {
        // description (X)

        // user (X)

        // datestarted (X)

        Todo newTodo = new Todo();
        newTodo.setDescription(tempTodo.getDescription());
        newTodo.setUser(tempTodo.getUser());
        newTodo.setDatestarted(tempTodo.getDatestarted());

        // Return newTodo
        return todorepos.save(newTodo);
    }
}

// Model - how the data is structured
// Controller - is where the front-end first contact the back-end
// Service - the actual back-end doing logic to make sure the data is correct
// Repository - the connection between the back-end code and the information that is being saved