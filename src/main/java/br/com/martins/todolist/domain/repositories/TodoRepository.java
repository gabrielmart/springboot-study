package br.com.martins.todolist.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.martins.todolist.domain.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
