package com.codestates.todo.be_todoProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class MultiResponseDto<T> {
    private List<T> data;
}
