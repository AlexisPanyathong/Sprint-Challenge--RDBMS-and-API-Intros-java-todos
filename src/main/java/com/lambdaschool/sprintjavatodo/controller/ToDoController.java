package com.lambdaschool.sprintjavatodo.controller;

import com.lambdaschool.sprintjavatodo.model.ToDo;
import com.lambdaschool.sprintjavatodo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// ToDo ENDPOINTS are here

@RestController
@RequestMapping
public class ToDoController {

    @Autowired
    ToDoService todoService;

    // PUT Request http://localhost:2019/todos/todoid/{todoid}
    @PutMapping(value = "/todoid/{todoid}")
    public ResponseEntity<?> updateTodo(@RequestBody ToDo updateTodo, @PathVariable long todoid) {
        todoService.update(updateTodo, todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
