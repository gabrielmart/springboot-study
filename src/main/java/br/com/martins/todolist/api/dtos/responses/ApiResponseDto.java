package br.com.martins.todolist.api.dtos.responses;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiResponseDto<T> {
  private HttpStatus status;
  private String message;
  private T data;
  private List<Object> errors;

  public ApiResponseDto(HttpStatus status, String message, T data, List<Object> errors) {
    this.status = status;
    this.message = message;
    this.data = data;
    this.errors = errors;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

  public List<Object> getErrors() {
    return errors;
  }

}
