package br.com.martins.todolist.api.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload for user creation")
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
