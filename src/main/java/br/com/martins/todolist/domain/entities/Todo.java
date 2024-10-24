package br.com.martins.todolist.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Boolean done;
    private Integer priority;

    @ManyToOne
    private User user;

    public Todo() {
    }

    // TODO: Validar campos nulos
    public Todo(Long id, String title, String description, Boolean done, Integer priority, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = (done != null) ? done : false;
        this.priority = priority;
        this.user = (user != null) ? user : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descricao) {
        this.description = descricao;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean realizado) {
        this.done = realizado;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priodade) {
        this.priority = priodade;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
