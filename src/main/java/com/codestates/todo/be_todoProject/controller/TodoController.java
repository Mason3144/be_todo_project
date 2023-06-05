package com.codestates.todo.be_todoProject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
public class TodoController {
    @PostMapping
    public ResponseEntity postTodo(){
        return null;
    }
    @GetMapping
    public ResponseEntity getTodos(){
        return null;
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(){
        return null;
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(){
        return null;
    }

    @DeleteMapping
    public ResponseEntity deleteTodos(){
        return null;
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(){
        return null;
    }
}
