package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler({NotFoundException.class})
  public static ResponseEntity notFoundException(NotFoundException ex) {
    Map<String, String> error = new HashMap<>();
    String fieldName = "errorDescription";
    String errorMessage = ex.getMessage();
    error.put(fieldName, errorMessage);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public static ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}
