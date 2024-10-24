package br.com.martins.todolist.api.mappers;

import java.util.List;

import br.com.martins.todolist.api.dtos.requests.TodoCreateRequestDto;
import br.com.martins.todolist.api.dtos.requests.TodoUpdateRequestDto;
import br.com.martins.todolist.api.dtos.responses.TodoResponseDto;
import br.com.martins.todolist.api.dtos.responses.UserResponseDto;
import br.com.martins.todolist.domain.entities.Todo;

public class TodoMapper {
  public static Todo toTodo(TodoCreateRequestDto todoRequestDto) {
    Todo todo = new Todo(
        null,
        todoRequestDto.getTitle(),
        todoRequestDto.getDescription(),
        null,
        todoRequestDto.getPriority(),
        null);

    return todo;
  }

  public static Todo toTodo(TodoUpdateRequestDto todoRequestDto) {
    Todo todo = new Todo(
        null,
        todoRequestDto.getTitle(),
        todoRequestDto.getDescription(),
        todoRequestDto.getDone(),
        todoRequestDto.getPriority(),
        null);

    return todo;
  }

  public static TodoResponseDto toTodoResponseDto(Todo todo) {
    UserResponseDto userResponseDto = UserMapper.toUserResponseDto(todo.getUser());

    return new TodoResponseDto(
        todo.getId(),
        todo.getTitle(),
        todo.getDescription(),
        todo.getDone(),
        todo.getPriority(),
        userResponseDto);
  }

  public static List<TodoResponseDto> toTodoResponseDtoList(List<Todo> todoList) {
    return todoList.stream().map(TodoMapper::toTodoResponseDto).toList();
  }
}
