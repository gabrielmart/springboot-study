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
import br.com.martins.todolist.api.dtos.responses.TodoResponseDto;
import br.com.martins.todolist.api.dtos.responses.ApiResponseDto;
import br.com.martins.todolist.api.mappers.TodoMapper;
import br.com.martins.todolist.domain.entities.Todo;
import br.com.martins.todolist.domain.services.TodoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @PostMapping
  public ResponseEntity<ApiResponseDto<TodoResponseDto>> create(
      @RequestBody @Valid TodoCreateRequestDto todoCreateRequestDto) {
    String username = getAuthenticatedUsername();

    Todo todo = TodoMapper.toTodo(todoCreateRequestDto);
    TodoResponseDto todoResponseDto = TodoMapper.toTodoResponseDto(todoService.save(todo, username));

    return buildResponse(HttpStatus.CREATED, "Todo criado com sucesso", todoResponseDto);
  }

  @GetMapping
  public ResponseEntity<ApiResponseDto<List<TodoResponseDto>>> list() {
    String username = getAuthenticatedUsername();

    List<TodoResponseDto> todoResponseDtoList = TodoMapper.toTodoResponseDtoList(todoService.list(username));

    return buildResponse(HttpStatus.OK, "Lista de todos", todoResponseDtoList);
  }

  @PutMapping("{id}")
  public ResponseEntity<ApiResponseDto<TodoResponseDto>> update(
      @PathVariable Long id,
      @RequestBody @Valid TodoUpdateRequestDto todoUpdateRequestDto) {
    String username = getAuthenticatedUsername();

    Todo todo = TodoMapper.toTodo(todoUpdateRequestDto);
    TodoResponseDto todoResponseDto = TodoMapper.toTodoResponseDto(todoService.update(id, todo, username));

    return buildResponse(HttpStatus.OK, "Todo atualizado com sucesso", todoResponseDto);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable("id") Long id) {
    String username = getAuthenticatedUsername();

    todoService.delete(id, username);

    return buildResponse(HttpStatus.OK, "Todo deletado com sucesso", null);
  }

  private String getAuthenticatedUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getName() != null && !authentication.getName().isEmpty()) {
      return authentication.getName();
    }
    throw new RuntimeException("Usuário não autenticado");
  }

  private <T> ResponseEntity<ApiResponseDto<T>> buildResponse(
      HttpStatus status, String message, T data) {
    ApiResponseDto<T> apiResponseDto = new ApiResponseDto<>(status, message, data, null);
    return ResponseEntity.status(status).body(apiResponseDto);
  }
}
