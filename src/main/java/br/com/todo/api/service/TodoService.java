package br.com.todo.api.service;

import br.com.todo.api.model.Todo;
import br.com.todo.api.repository.TodoRepository;
import br.com.todo.api.repository.filter.TodoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo salvar(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> buscarTodos(TodoFilter todoFilter) {
        return todoRepository.filtrar(todoFilter);
    }

    public Todo buscarPeloCodigo(Long codigo) {
        Optional<Todo> todo = todoRepository.findById(codigo);
        return todo.get();
    }

    public void deletar(Long codigo) {
        Todo todo = buscarPeloCodigo (codigo);
        todoRepository.delete(todo);
    }
}
