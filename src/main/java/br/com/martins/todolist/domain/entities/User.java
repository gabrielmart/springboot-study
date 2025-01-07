package br.com.martins.todolist.domain.entities;

import java.util.List;

import br.com.martins.todolist.domain.entities.enums.EnumRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// TODO: Adicionar email para validação
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;
  private String password;

  @Enumerated(EnumType.STRING)
  private EnumRole role;

  @OneToMany(mappedBy = "user")
  private List<Todo> todo;

  public User() {
    this.role = (role != null) ? role : EnumRole.CUSTOMER;
  }

  public User(Long id, String username, String password, EnumRole role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = (role != null) ? role : EnumRole.CUSTOMER;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public EnumRole getRole() {
    return role;
  }

  public void setRole(EnumRole role) {
    this.role = role;
  }

  public List<Todo> getTodo() {
    return todo;
  }

  public void setTodo(List<Todo> todo) {
    this.todo = todo;
  }

}
