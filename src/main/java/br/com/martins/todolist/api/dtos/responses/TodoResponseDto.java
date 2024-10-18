package br.com.martins.todolist.api.dtos.responses;

public class TodoResponseDto {
  private final Long id;
  private final String titulo;
  private final String descricao;
  private final Boolean realizado;
  private final Integer prioridade;

  public TodoResponseDto(Long id, String titulo, String descricao, Boolean realizado, Integer prioridade) {
    this.id = id;
    this.titulo = titulo;
    this.descricao = descricao;
    this.realizado = realizado;
    this.prioridade = prioridade;
  }

  public Long getId() {
    return id;
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
