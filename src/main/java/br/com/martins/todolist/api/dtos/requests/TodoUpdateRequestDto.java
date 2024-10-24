package br.com.martins.todolist.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TodoUpdateRequestDto {
  @NotBlank(message = "O Titulo deve ser informado")
  private final String title;

  @NotBlank(message = "A Descrição deve ser informada")
  private final String description;

  private final Boolean done;

  @NotNull(message = "A prioridade deve ser informada")
  private final Integer priority;

  public TodoUpdateRequestDto(String title, String description, Boolean done, Integer priority) {
    this.title = title;
    this.description = description;
    this.done = done;
    this.priority = priority;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getDone() {
    return done;
  }

  public Integer getPriority() {
    return priority;
  }
}
