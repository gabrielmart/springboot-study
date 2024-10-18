package br.com.martins.todolist.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.martins.todolist.exceptions.TodoNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler(TodoNotFoundException.class)
  private ResponseEntity<String> TodoNotFoundExceptionHandler(TodoNotFoundException todoNotFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(todoNotFoundException.getMessage());
  }
}
