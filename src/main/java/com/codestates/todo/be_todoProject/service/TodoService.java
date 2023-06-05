package com.codestates.todo.be_todoProject.service;

import com.codestates.todo.be_todoProject.entity.Todos;
import com.codestates.todo.be_todoProject.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todos createTodo(Todos todos){
        return null;
    }

    public Todos updateTodo(Todos todos){
        return null;
    }

    public void removeTodos(){

    }

    public void removeTodo(Todos todos){

    }

    public Todos findTodo(long todoId){
        return null;
    }

    public List<Todos> findTodos(){
        return null;
    }
}
