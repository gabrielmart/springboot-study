package br.com.martins.todolist.api.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload for authentication containing username and password")
public class AuthenticationRequestDto {
  @Schema(description = "Username of the user", example = "gabrielmart")
  private String username;

  @Schema(description = "Password of the user", example = "password123")
  private String password;

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
}
