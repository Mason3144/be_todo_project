package com.codestates.todo.be_todoProject.exception.businessLogicException;

import lombok.Getter;

public enum ExceptionCode {
    TODO_NOT_FOUND(400, "Todo not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
