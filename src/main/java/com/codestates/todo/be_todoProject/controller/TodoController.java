package com.codestates.todo.be_todoProject.controller;

import com.codestates.todo.be_todoProject.dto.MultiResponseDto;
import com.codestates.todo.be_todoProject.dto.SingleResponseDto;
import com.codestates.todo.be_todoProject.dto.TodoDto;
import com.codestates.todo.be_todoProject.entity.Todos;
import com.codestates.todo.be_todoProject.mapper.TodoMapper;
import com.codestates.todo.be_todoProject.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@CrossOrigin
@Validated
public class TodoController {
    private final TodoService service;
    private final TodoMapper mapper;

    public TodoController(TodoService service, TodoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@RequestBody @Valid TodoDto.Post requestBody){
        Todos todo = service.createTodo(mapper.todoDtoPostToTodos(requestBody));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.todosToTodoDtoResponse(todo)), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity getTodos(){
        List<Todos> todos = service.findTodos();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.todosToTodoDtoResponse(todos)), HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive long todoId){
        Todos todo = service.findTodo(todoId);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.todosToTodoDtoResponse(todo)), HttpStatus.OK);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive long todoId,
                                    @RequestBody @Valid TodoDto.Patch requestBody){
        requestBody.setId(todoId);
        Todos todo = service.updateTodo(mapper.todoDtoPatchToTodos(requestBody));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.todosToTodoDtoResponse(todo)), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos(){
        service.removeTodos();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") @Positive long todoId){
        service.removeTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
