package br.com.martins.todolist.domain.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.martins.todolist.domain.entities.Todo;
import br.com.martins.todolist.domain.entities.User;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  List<Todo> findByUser(User user, Sort sort);

  boolean existsByIdAndUser(Long id, User user);

}
