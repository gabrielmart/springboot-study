package br.com.martins.todolist.api.mappers;

import br.com.martins.todolist.api.dtos.requests.UserCreateRequestDto;
import br.com.martins.todolist.api.dtos.responses.UserResponseDto;
import br.com.martins.todolist.domain.entities.User;

public class UserMapper {
  public static User toUser(UserCreateRequestDto userCreateRequestDto) {
    User user = new User(
        null,
        userCreateRequestDto.getUsername(),
        userCreateRequestDto.getPassword(),
        null);

    return user;
  }

  public static UserResponseDto toUserResponseDto(User user) {
    return new UserResponseDto(user.getId(), user.getUsername());
  }
}
