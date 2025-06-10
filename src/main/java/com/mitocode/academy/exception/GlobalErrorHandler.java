package com.mitocode.academy.exception;

import com.mitocode.academy.dto.GenericResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleDefaultException(Exception ex, WebRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse(LocalDateTime.now(),
                                        ex.getMessage(),
                                        request.getDescription(false) );

        return new ResponseEntity<>(new GenericResponse<>(500, "error", List.of(cer)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(new GenericResponse<>(404, "not-found", List.of(cer)), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse(LocalDateTime.now(),
                messageFromFieldErrors(ex),
                request.getDescription(false));

        return new ResponseEntity<>(cer, HttpStatus.BAD_REQUEST);
    }

    private String messageFromFieldErrors(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .forEach((m) -> sb.append(m).append(".").append(String.format("%n")));
        return sb.toString();
    }
}
