package br.com.martins.todolist.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.martins.todolist.domain.entities.Todo;
import br.com.martins.todolist.domain.entities.User;
import br.com.martins.todolist.domain.repositories.TodoRepository;
import br.com.martins.todolist.domain.repositories.UserRepository;
import br.com.martins.todolist.exceptions.TodoNotFoundException;

@Service
@Transactional
public class TodoService {
  private final TodoRepository todoRepository;
  private final UserRepository userRepository;

  public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
    this.todoRepository = todoRepository;
    this.userRepository = userRepository;
  }

  private User findUserByUsername(String username, String errorMessage) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(errorMessage));
  }

  public Todo save(Todo todo, String username) {
    User user = findUserByUsername(username, "Não foi possivel encontrar o usuário para salvar o Todo informado");
    todo.setUser(user);
    return todoRepository.save(todo);
  }

  public List<Todo> list(String username) {
    User user = findUserByUsername(username, "Não foi possivel encontrar o usuário para listar os Todos");

    Sort sort = Sort.by("priority").descending().and(Sort.by("title").ascending());
    return todoRepository.findByUser(user, sort);
  }

  public Optional<Todo> findById(Long id) {
    return todoRepository.findById(id);
  }

  public Todo update(Long id, Todo todo, String username) {
    User user = findUserByUsername(username, "Não foi possivel encontrar o usuário para atualizar o Todo");

    if (!todoRepository.existsByIdAndUser(id, user)) {
      throw new TodoNotFoundException("O ID do Todo informado para atualização não existe");
    }

    todo.setId(id);
    todo.setUser(user);
    return todoRepository.save(todo);
  }

  public List<Todo> delete(Long id, String username) {
    User user = findUserByUsername(username, "Não foi possivel encontrar o usuário para deletar o Todo informado");

    if (!todoRepository.existsByIdAndUser(id, user)) {
      throw new TodoNotFoundException("O ID do Todo informado para exclusão não existe");
    }

    todoRepository.deleteById(id);
    return list(username);
  }
}
