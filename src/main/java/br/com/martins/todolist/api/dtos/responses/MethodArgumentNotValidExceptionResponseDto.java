package br.com.martins.todolist.api.dtos.responses;

public class MethodArgumentNotValidExceptionResponseDto {
  private String field;
  private String message;

  public MethodArgumentNotValidExceptionResponseDto(String field, String message) {
    this.field = field;
    this.message = message;
  }

  public String getField() {
    return field;
  }

  public String getMessage() {
    return message;
  }

}
