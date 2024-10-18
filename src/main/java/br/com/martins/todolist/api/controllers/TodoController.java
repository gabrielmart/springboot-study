package br.com.martins.todolist.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.com.martins.todolist.api.mappers.TodoMapper;
import br.com.martins.todolist.domain.entities.Todo;
import br.com.martins.todolist.domain.services.TodoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {
  private TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @PostMapping
  public ResponseEntity<TodoResponseDto> create(@RequestBody @Valid TodoCreateRequestDto todoCreateRequestDto) {
    Todo todo = TodoMapper.toTodo(todoCreateRequestDto);
    TodoResponseDto todoResponseDto = TodoMapper.toTodoResponseDto(todoService.save(todo));
    return ResponseEntity.status(HttpStatus.CREATED).body(todoResponseDto);
  }

  @GetMapping
  public ResponseEntity<List<TodoResponseDto>> list() {
    List<TodoResponseDto> todoResponseDtoList = TodoMapper.toTodoResponseDtoList(todoService.list());
    return ResponseEntity.status(HttpStatus.OK).body(todoResponseDtoList);
  }

  @PutMapping("{id}")
  public ResponseEntity<TodoResponseDto> update(@PathVariable Long id,
      @RequestBody @Valid TodoUpdateRequestDto todoUpdateRequestDto) {
    Todo todo = TodoMapper.toTodo(todoUpdateRequestDto);
    TodoResponseDto todoResponseDto = TodoMapper.toTodoResponseDto(todoService.update(id, todo));
    return ResponseEntity.status(HttpStatus.OK).body(todoResponseDto);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    todoService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
