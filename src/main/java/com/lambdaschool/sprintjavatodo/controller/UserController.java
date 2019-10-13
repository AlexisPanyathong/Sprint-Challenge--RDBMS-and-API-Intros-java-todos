package com.lambdaschool.sprintjavatodo.controller;

// This is where the ENDPOINTS are

import com.lambdaschool.sprintjavatodo.model.Todo;
import com.lambdaschool.sprintjavatodo.model.User;
import com.lambdaschool.sprintjavatodo.service.TodoService;
import com.lambdaschool.sprintjavatodo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    // GET Request http://localhost:2019/users/mine
    @GetMapping(value = "/mine",
                produces = {"application/json"})
    public ResponseEntity<?> getLoggedInUser(Authentication authentication) {

        return new ResponseEntity<>(userService.findUserByName(authentication.getName()), HttpStatus.OK);

    }

    // POST Request http://localhost:2019/users
    @PostMapping(value = "",
                consumes = {"application/json"},
                produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException {
        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    // POST Request http://localhost:2019/users/todo/{userid}
    @PostMapping(value = "/todo/{userid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewTodo(@PathVariable Long userid, @RequestBody Todo newTodo)
    {
        Todo tempTodo = new Todo(newTodo.getDescription(), userService.findUserById(userid), new Date());
        tempTodo = todoService.save(tempTodo);
        return new ResponseEntity<>(tempTodo, HttpStatus.CREATED);
    }

    // DELETE Request http://localhost:2019/users/userid/{userid}
    @DeleteMapping("/userid/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
