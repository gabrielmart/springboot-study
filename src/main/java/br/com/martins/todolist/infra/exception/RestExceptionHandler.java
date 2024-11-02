package br.com.martins.todolist.infra.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.martins.todolist.api.dtos.responses.ApiResponseDto;
import br.com.martins.todolist.api.dtos.responses.MethodArgumentNotValidExceptionResponseDto;
import br.com.martins.todolist.exceptions.SubjectNotInformedException;
import br.com.martins.todolist.exceptions.TodoNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponseDto<Void>> handleValidationException(MethodArgumentNotValidException ex) {
    List<Object> errors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(error -> new MethodArgumentNotValidExceptionResponseDto(error.getField(), error.getDefaultMessage()))
        .collect(Collectors.toList());

    ApiResponseDto<Void> apiResponse = new ApiResponseDto<>(HttpStatus.BAD_REQUEST,
        "Validação falhou. Confira os erros de campo para correções", null, errors);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @ExceptionHandler(TodoNotFoundException.class)
  private ResponseEntity<ApiResponseDto<Void>> todoNotFoundExceptionHandler(
      TodoNotFoundException todoNotFoundException) {
    ApiResponseDto<Void> apiResponse = new ApiResponseDto<>(HttpStatus.NOT_FOUND, todoNotFoundException.getMessage(),
        null, null);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  private ResponseEntity<ApiResponseDto<Void>> usernameNotFoundExceptionHandler(
      UsernameNotFoundException usernameNotFoundException) {
    ApiResponseDto<Void> apiResponse = new ApiResponseDto<>(HttpStatus.NOT_FOUND,
        usernameNotFoundException.getMessage(), null, null);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @ExceptionHandler(SubjectNotInformedException.class)
  private ResponseEntity<ApiResponseDto<Void>> subjectNotInformedExceptionHandler(
      SubjectNotInformedException subjectNotInformedException) {
    ApiResponseDto<Void> apiResponse = new ApiResponseDto<>(HttpStatus.BAD_REQUEST,
        subjectNotInformedException.getMessage(), null, null);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponseDto<Void>> handleGenericException(Exception ex) {
    ApiResponseDto<Void> apiResponse = new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR,
        "Erro interno do servidor", null, Arrays.asList(ex.getMessage()));
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }
}
