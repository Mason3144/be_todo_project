package com.codestates.todo.be_todoProject.exception.advice;

import com.codestates.todo.be_todoProject.exception.businessLogicException.BusinessLogicException;
import com.codestates.todo.be_todoProject.exception.errorResponse.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBindException(
            BindException e) {
        final ErrorResponse response = ErrorResponse.of(e);

        return response;
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        final ErrorResponse response = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
