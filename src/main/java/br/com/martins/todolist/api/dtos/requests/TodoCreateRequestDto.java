package br.com.martins.todolist.api.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Payload for todo creation")
public class TodoCreateRequestDto {
  @NotBlank(message = "O Titulo deve ser informado")
  private final String title;

  @NotBlank(message = "A Descrição deve ser informada")
  private final String description;

  @NotNull(message = "A prioridade deve ser informada")
  private final Integer priority;

  public TodoCreateRequestDto(String title, String description, Integer priority) {
    this.title = title;
    this.description = description;
    this.priority = priority;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Integer getPriority() {
    return priority;
  }
}
