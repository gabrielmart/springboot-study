package br.com.martins.todolist.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

  @Test
  void shouldCreateTodoWithDefaultConstructor() {
    Todo todo = new Todo();

    assertNull(todo.getId());
    assertNull(todo.getTitle());
    assertNull(todo.getDescription());
    assertFalse(todo.getDone());
    assertNull(todo.getPriority());
  }

  @Test
  void shouldCreateTodoWithParameterizedConstructor() {
    User user = new User();
    user.setId(1L);

    Todo todo = new Todo(1L, "Test Title", "Test description", true, 0, user);

    assertEquals(1L, todo.getId());
    assertEquals("Test Title", todo.getTitle());
    assertEquals("Test description", todo.getDescription());
    assertTrue(todo.getDone());
    assertEquals(0, todo.getPriority());
    assertEquals(user, todo.getUser());
  }

  @Test
  void shouldSetDefaultDoneWhenPassedToConstructor() {
    Todo todo = new Todo(1L, "Test Title", "Test Description", null, 3, null);

    assertFalse(todo.getDone());
  }

  @Test
  void shouldSetNullWhenNotInformedUser() {
    Todo todo = new Todo(1L, "Test Title", "Test Description", false, 3, null);

    assertNull(todo.getUser());
  }
}
