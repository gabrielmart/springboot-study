package br.com.martins.todolist.api.dtos.responses;

public class TokenResponseDto {
  private String token;

  public TokenResponseDto(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

}
