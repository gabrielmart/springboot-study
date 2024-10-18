package br.com.martins.todolist.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Boolean realizado;
    private Integer prioridade;

    public Todo() {
    }

    // TODO: Validar campos nulos
    public Todo(Long id, String titulo, String descricao, Boolean realizado, Integer prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.realizado = (realizado != null) ? realizado : false;
        this.prioridade = prioridade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String name) {
        this.titulo = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getRealizado() {
        return realizado;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer priodade) {
        this.prioridade = priodade;
    }

}
