package br.com.martins.todolist.api.mappers;

import java.util.List;

import br.com.martins.todolist.api.dtos.requests.TodoCreateRequestDto;
import br.com.martins.todolist.api.dtos.requests.TodoUpdateRequestDto;
import br.com.martins.todolist.api.dtos.responses.TodoResponseDto;
import br.com.martins.todolist.domain.entities.Todo;

public class TodoMapper {
  public static Todo toTodo(TodoCreateRequestDto todoRequestDto) {
    Todo todo = new Todo(
        null,
        todoRequestDto.getTitulo(),
        todoRequestDto.getDescricao(),
        null,
        todoRequestDto.getPrioridade());

    return todo;
  }

  public static Todo toTodo(TodoUpdateRequestDto todoRequestDto) {
    Todo todo = new Todo(
        null,
        todoRequestDto.getTitulo(),
        todoRequestDto.getDescricao(),
        todoRequestDto.getRealizado(),
        todoRequestDto.getPrioridade());

    return todo;
  }

  public static TodoResponseDto toTodoResponseDto(Todo todo) {
    return new TodoResponseDto(
        todo.getId(),
        todo.getTitulo(),
        todo.getDescricao(),
        todo.getRealizado(),
        todo.getPrioridade());
  }

  public static List<TodoResponseDto> toTodoResponseDtoList(List<Todo> todoList) {
    return todoList.stream().map(TodoMapper::toTodoResponseDto).toList();
  }
}
