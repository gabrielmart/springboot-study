package br.com.martins.todolist.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.martins.todolist.api.dtos.requests.UserCreateRequestDto;
import br.com.martins.todolist.api.dtos.responses.ApiResponseDto;
import br.com.martins.todolist.api.dtos.responses.UserResponseDto;
import br.com.martins.todolist.api.mappers.UserMapper;
import br.com.martins.todolist.domain.entities.User;
import br.com.martins.todolist.domain.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "Endpoints for managing Users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Create a user", description = "Creates a new user in the system.", responses = {
      @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)))
  })
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
