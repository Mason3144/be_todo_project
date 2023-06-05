package com.codestates.todo.be_todoProject.dto;


import com.codestates.todo.be_todoProject.validations.notSpace.NotSpace;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TodoDto {
    @Getter
    @Builder
    public static class Post{
        @NotBlank
        private String title;
        @NotNull
        private int todoOrder;
        @NotNull
        private boolean completed;
    }

    @Getter
    @Builder
    @Setter
    public static class Patch{
        private long id;
        private String title;
        private Integer todoOrder;
        private Boolean completed;
    }

    @Getter
    @Builder
    public static class Response{
        private long id;
        private String title;
        private int todoOrder;
        private boolean completed;
    }
}
