package br.com.martins.todolist.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TodoUpdateRequestDto {
  @NotBlank(message = "O Titulo deve ser informado")
  private final String titulo;

  @NotBlank(message = "A Descrição deve ser informada")
  private final String descricao;

  private final Boolean realizado;
  
  @NotNull(message = "A prioridade deve ser informada")
  private final Integer prioridade;

  public TodoUpdateRequestDto(String titulo, String descricao, Boolean realizado, Integer prioridade) {
    this.titulo = titulo;
    this.descricao = descricao;
    this.realizado = realizado;
    this.prioridade = prioridade;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public Boolean getRealizado() {
    return realizado;
  }

  public Integer getPrioridade() {
    return prioridade;
  }
}
