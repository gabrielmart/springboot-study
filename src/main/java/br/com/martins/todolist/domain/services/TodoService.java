package br.com.martins.todolist.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.martins.todolist.domain.entities.Todo;
import br.com.martins.todolist.domain.repositories.TodoRepository;
import br.com.martins.todolist.exceptions.TodoNotFoundException;

@Service
@Transactional
public class TodoService {
  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todo save(Todo todo) {
    Todo todoSaved = todoRepository.save(todo);
    return todoSaved;
  }

  public List<Todo> list() {
    Sort sort = Sort.by("prioridade").descending().and(
        Sort.by("titulo").ascending());

    return todoRepository.findAll(sort);
  }

  public Optional<Todo> findById(Long id) {
    return todoRepository.findById(id);
  }

  public Todo update(Long id, Todo todo) {
    Boolean isTodoExisting = todoRepository.existsById(id);

    if (!isTodoExisting) {
      throw new TodoNotFoundException("O ID do Todo informado para atualização, não existe");
    }

    todo.setId(id);
    Todo todoUpdated = todoRepository.save(todo);

    return todoUpdated;
  }

  public List<Todo> delete(Long id) {
    Boolean isTodoExisting = todoRepository.existsById(id);

    if (!isTodoExisting) {
      throw new TodoNotFoundException("O ID do Todo informado para exclusão, não existe");
    }

    todoRepository.deleteById(id);
    return list();
  }
}
