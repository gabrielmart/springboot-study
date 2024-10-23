package br.com.martins.todolist.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.martins.todolist.api.dtos.requests.UserCreateRequestDto;
import br.com.martins.todolist.api.dtos.responses.UserResponseDto;
import br.com.martins.todolist.api.mappers.UserMapper;
import br.com.martins.todolist.domain.entities.User;
import br.com.martins.todolist.domain.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping()
  public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserCreateRequestDto userCreateRequestDto) {
    User user = UserMapper.toUser(userCreateRequestDto);
    UserResponseDto userResponseDto = UserMapper.toUserResponseDto(userService.save(user));

    return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
  }

}
