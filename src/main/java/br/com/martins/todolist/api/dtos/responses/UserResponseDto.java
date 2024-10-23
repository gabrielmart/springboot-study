package br.com.martins.todolist.api.dtos.responses;

public class UserResponseDto {

  private final Long id;
  private final String username;

  public UserResponseDto(Long id, String username) {
    this.id = id;
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public Long getid() {
    return id;
  }

}
