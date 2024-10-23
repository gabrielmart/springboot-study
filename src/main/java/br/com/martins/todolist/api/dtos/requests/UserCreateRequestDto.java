package br.com.martins.todolist.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateRequestDto {
  @NotBlank(message = "O username deve ser informado")
  private final String username;

  @NotBlank(message = "O password deve ser informado")
  @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
  private final String password;

  public UserCreateRequestDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}
