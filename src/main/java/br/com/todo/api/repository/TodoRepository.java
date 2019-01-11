package br.com.todo.api.repository;

import br.com.todo.api.model.Todo;
import br.com.todo.api.repository.todo.TodoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryQuery {
}