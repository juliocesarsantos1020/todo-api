package br.com.todo.api.repository.todo;

import br.com.todo.api.model.Todo;
import br.com.todo.api.repository.filter.TodoFilter;

import java.util.List;

public interface TodoRepositoryQuery {

    public List<Todo> filtrar(TodoFilter todoFilter);
}
