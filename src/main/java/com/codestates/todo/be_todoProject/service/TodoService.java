package com.codestates.todo.be_todoProject.service;

import com.codestates.todo.be_todoProject.entity.Todos;
import com.codestates.todo.be_todoProject.exception.businessLogicException.BusinessLogicException;
import com.codestates.todo.be_todoProject.exception.businessLogicException.ExceptionCode;
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
        System.out.println(todos.getId()+" "+ todos.getTodoOrder()+" "+ todos.getTitle()+" "+ todos.getCompleted());

        Todos foundTodo = findById(todos.getId());

        System.out.println(foundTodo.getId()+" "+ foundTodo.getTodoOrder()+" "+ foundTodo.getTitle()+" "+ foundTodo.getCompleted());

        Optional.ofNullable(todos.getTodoOrder()).ifPresent(foundTodo::setTodoOrder);
        Optional.ofNullable(todos.getTitle()).ifPresent(foundTodo::setTitle);
        Optional.ofNullable(todos.getCompleted()).ifPresent(foundTodo::setCompleted);

        return repository.save(foundTodo);
    }

    public void removeTodos(){
        repository.deleteAll();
    }

    public void removeTodo(long todoId){
        repository.delete(findById(todoId));
    }

    public Todos findTodo(long todoId){
        return findById(todoId);
    }

    public List<Todos> findTodos(){
        return repository.findAll();
    }

    private Todos findById(long todoId){
        Optional<Todos> optionalTodos = repository.findById(todoId);
        return optionalTodos.orElseThrow(()-> new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
    }
}
