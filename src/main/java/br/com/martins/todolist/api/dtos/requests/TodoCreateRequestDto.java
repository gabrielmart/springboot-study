package br.com.martins.todolist.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TodoCreateRequestDto {
  @NotBlank(message = "O Titulo deve ser informado")
  private final String titulo;

  @NotBlank(message = "A Descrição deve ser informada")
  private final String descricao;

  @NotNull(message = "A prioridade deve ser informada")
  private final Integer prioridade;

  public TodoCreateRequestDto(String titulo, String descricao, Integer prioridade) {
    this.titulo = titulo;
    this.descricao = descricao;
    this.prioridade = prioridade;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public Integer getPrioridade() {
    return prioridade;
  }
}
