package com.codestates.todo.be_todoProject.service;

import com.codestates.todo.be_todoProject.entity.Todos;
import com.codestates.todo.be_todoProject.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todos createTodo(Todos todos){
        return repository.save(todos);
    }

    public Todos updateTodo(Todos todos){
        Todos foundTodo = repository.findById(todos.getId()).get();

        Optional.of(todos.getTodoOrder()).ifPresent(foundTodo::setTodoOrder);
        Optional.of(todos.getTitle()).ifPresent(foundTodo::setTitle);
        Optional.of(todos.isCompleted()).ifPresent(foundTodo::setCompleted);

        return repository.save(foundTodo);
    }

    public void removeTodos(){
        repository.deleteAll();
    }

    public void removeTodo(long todoId){
        repository.delete(repository.findById(todoId).get());
    }

    public Todos findTodo(long todoId){
        return repository.findById(todoId).get();
    }

    public List<Todos> findTodos(){
        return repository.findAll();
    }
}
