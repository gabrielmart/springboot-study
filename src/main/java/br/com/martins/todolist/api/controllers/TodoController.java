package br.com.martins.todolist.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.martins.todolist.api.dtos.requests.TodoCreateRequestDto;
import br.com.martins.todolist.api.dtos.requests.TodoUpdateRequestDto;
import br.com.martins.todolist.api.dtos.responses.ApiResponseDto;
import br.com.martins.todolist.api.dtos.responses.TodoResponseDto;
import br.com.martins.todolist.api.mappers.TodoMapper;
import br.com.martins.todolist.domain.entities.Todo;
import br.com.martins.todolist.domain.services.TodoService;
import br.com.martins.todolist.exceptions.UnauthorizedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/todos")
@Tag(name = "Todo", description = "Endpoints for managing Todos") // Adicionando a tag globalmente
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @Operation(description = "Creates a new Todo for the authenticated user.", security = @SecurityRequirement(name = "Bearer Authentication"), responses = {
      @ApiResponse(responseCode = "201", description = "Todo created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
  })
  @PostMapping
  public ResponseEntity<ApiResponseDto<TodoResponseDto>> create(
      @RequestBody @Valid TodoCreateRequestDto todoCreateRequestDto) {
    String username = getAuthenticatedUsername();

    Todo todo = TodoMapper.toTodo(todoCreateRequestDto);
    TodoResponseDto todoResponseDto = TodoMapper.toTodoResponseDto(todoService.save(todo, username));

    return buildResponse(HttpStatus.CREATED, "Todo created successfully", todoResponseDto);
  }

  @Operation(description = "Returns a list of all Todos for the authenticated user.", security = @SecurityRequirement(name = "Bearer Authentication"), responses = {
      @ApiResponse(responseCode = "200", description = "Todo list retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
      @ApiResponse(responseCode = "401", description = "User not authenticated", content = @Content)
  })
  @GetMapping
  public ResponseEntity<ApiResponseDto<List<TodoResponseDto>>> list() {
    String username = getAuthenticatedUsername();

    List<TodoResponseDto> todoResponseDtoList = TodoMapper.toTodoResponseDtoList(todoService.list(username));

    return buildResponse(HttpStatus.OK, "Todo list", todoResponseDtoList);
  }

  @Operation(description = "Updates the details of an existing task by its ID linked to the authenticated user.", security = @SecurityRequirement(name = "Bearer Authentication"), responses = {
      @ApiResponse(responseCode = "200", description = "Todo updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
      @ApiResponse(responseCode = "404", description = "Todo not found", content = @Content)
  })
  @PutMapping("{id}")
  public ResponseEntity<ApiResponseDto<TodoResponseDto>> update(
      @PathVariable Long id,
      @RequestBody @Valid TodoUpdateRequestDto todoUpdateRequestDto) {
    String username = getAuthenticatedUsername();

    Todo todo = TodoMapper.toTodo(todoUpdateRequestDto);
    TodoResponseDto todoResponseDto = TodoMapper.toTodoResponseDto(todoService.update(id, todo, username));

    return buildResponse(HttpStatus.OK, "Todo updated successfully", todoResponseDto);
  }

  @Operation(description = "Deletes an existing Todo by its ID linked to the authenticated user.", security = @SecurityRequirement(name = "Bearer Authentication"), responses = {
      @ApiResponse(responseCode = "200", description = "Todo deleted successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
      @ApiResponse(responseCode = "404", description = "Todo not found", content = @Content)
  })
  @DeleteMapping("{id}")
  public ResponseEntity<ApiResponseDto<Void>> delete(
      @Parameter(description = "ID of the Todo to be deleted") @PathVariable("id") Long id) {
    String username = getAuthenticatedUsername();

    todoService.delete(id, username);

    return buildResponse(HttpStatus.OK, "Todo deleted successfully", null);
  }

  private String getAuthenticatedUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getName() != null && !authentication.getName().isEmpty()) {
      return authentication.getName();
    }
    throw new UnauthorizedException("User not authenticated");
  }

  private <T> ResponseEntity<ApiResponseDto<T>> buildResponse(
      HttpStatus status, String message, T data) {
    ApiResponseDto<T> apiResponseDto = new ApiResponseDto<>(status, message, data, null);
    return ResponseEntity.status(status).body(apiResponseDto);
  }
}
