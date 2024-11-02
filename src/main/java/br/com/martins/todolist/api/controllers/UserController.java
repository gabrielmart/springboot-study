package br.com.martins.todolist.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.martins.todolist.api.dtos.requests.UserCreateRequestDto;
import br.com.martins.todolist.api.dtos.responses.UserResponseDto;
import br.com.martins.todolist.api.dtos.responses.ApiResponseDto;
import br.com.martins.todolist.api.mappers.UserMapper;
import br.com.martins.todolist.domain.entities.User;
import br.com.martins.todolist.domain.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<ApiResponseDto<UserResponseDto>> create(
      @RequestBody @Valid UserCreateRequestDto userCreateRequestDto) {

    User user = UserMapper.toUser(userCreateRequestDto);
    UserResponseDto userResponseDto = UserMapper.toUserResponseDto(userService.save(user));

    ApiResponseDto<UserResponseDto> apiResponseDto = new ApiResponseDto<>(
        HttpStatus.CREATED,
        "Usuário criado com sucesso",
        userResponseDto,
        null);

    return ResponseEntity.status(apiResponseDto.getStatus()).body(apiResponseDto);
  }
}
