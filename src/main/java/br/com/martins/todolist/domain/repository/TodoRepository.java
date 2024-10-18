package br.com.martins.todolist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.martins.todolist.domain.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
