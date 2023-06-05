package com.codestates.todo.be_todoProject.exception.errorResponse;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;

    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private ErrorResponse(final List<FieldError> fieldErrors,
                          final List<ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    public static ErrorResponse of(BindException bindException) {
        return new ErrorResponse(FieldError.of(bindException), null);
    }


    public static ErrorResponse of(HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    @Getter
    private static class FieldError{
        private FieldSource source;
        private String field;
        private Object rejectedValue;
        private String reason;

        public enum FieldSource{
            QUERY_PARAMETER("Query Parameter"),
            REQUEST_BODY("Request Body");
            @Getter
            private String source;
            FieldSource(String source) {
                this.source = source;
            }
        }
        public FieldError(FieldSource source, String field, Object rejectedValue, String reason) {
            this.source = source; // 추가 코드 3
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }
        public static List<FieldError> of(BindException bindException){
            final List<org.springframework.validation.FieldError> fieldErrors =
                    bindException.getFieldErrors();
            return fieldErrors.stream()
                    .map(e-> new FieldError(
                            bindException instanceof MethodArgumentNotValidException ?
                                    FieldSource.QUERY_PARAMETER : FieldSource.REQUEST_BODY ,
                            e.getField(),
                            e.getRejectedValue() == null ? "" : e.getRejectedValue().toString(),
                            e.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class ConstraintViolationError {
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        private ConstraintViolationError(String propertyPath, Object rejectedValue,
                                   String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<ConstraintViolationError> of(
                Set<ConstraintViolation<?>> constraintViolations) {
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()
                    )).collect(Collectors.toList());
        }
    }
}