package br.com.martins.todolist.domain.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.martins.todolist.domain.entity.Todo;
import br.com.martins.todolist.domain.repository.TodoRepository;

@Service
@Transactional
public class TodoService {
  private final  TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<Todo> create(Todo todo) {
    todoRepository.save(todo);
    return list();
  }

  public List<Todo> list() {
    Sort sort = Sort.by("prioridade").descending().and(
        Sort.by("nome").ascending());

    return todoRepository.findAll(sort);
  }

  public List<Todo> update(Todo todo) {
    todoRepository.save(todo);
    return list();

  }

  public List<Todo> delete(Long id) {
    todoRepository.deleteById(id);
    return list();
  }
}
