package com.codestates.todo.be_todoProject.repository;

import com.codestates.todo.be_todoProject.entity.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todos, Long> {
}
