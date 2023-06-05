package com.codestates.todo.be_todoProject.mapper;

import com.codestates.todo.be_todoProject.dto.TodoDto;
import com.codestates.todo.be_todoProject.entity.Todos;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
    Todos todoDtoPostToTodos(TodoDto.Post todoDtoPost);
    Todos todoDtoPatchToTodos(TodoDto.Patch todoDtopatch);
    TodoDto.Response todosToTodoDtoResponse(Todos todos);
    List<TodoDto.Response> todosToTodoDtoResponse(List<Todos> todos);
}
