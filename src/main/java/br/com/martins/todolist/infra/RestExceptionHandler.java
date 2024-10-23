package br.com.martins.todolist.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.martins.todolist.exceptions.TodoNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler(TodoNotFoundException.class)
  private ResponseEntity<String> TodoNotFoundExceptionHandler(TodoNotFoundException todoNotFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(todoNotFoundException.getMessage());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  private ResponseEntity<String> UsernameNotFoundExceptionHandler(UsernameNotFoundException usernameNotFoundException) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(usernameNotFoundException.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String,String>> ValidationExceptionsHandler(
      MethodArgumentNotValidException methodArgumentNotValidException) {
    Map<String, String> errors = new HashMap<>();
    methodArgumentNotValidException.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
  }
}
