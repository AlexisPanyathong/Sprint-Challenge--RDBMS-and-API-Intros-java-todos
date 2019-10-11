package com.lambdaschool.sprintjavatodo.service;

import com.lambdaschool.sprintjavatodo.model.ToDo;
import com.lambdaschool.sprintjavatodo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoRepository todorepos;

    @Override
    public List<ToDo> findAll() {
        List<ToDo> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public ToDo findToDoById(long id) {

        return todorepos.findAllById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

    }

    @Override
    public void delete(long id) {

        if (todorepos.findAllById(id).isPresent()) {
            todorepos.deleteById(id);
        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public ToDo save(ToDo todo) {

        return todorepos.save(todo);
    }

    @Override
    public List<ToDo> findByUserName(String username) {
        List<ToDo> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }

    @Override
    public ToDo update(ToDo todo, long id) {
        ToDo newToDo = todorepos.findAllById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        newToDo.setCompleted(true);
        return todorepos.save(newToDo);
    }

}
