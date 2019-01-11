package br.com.todo.api.resource;

import br.com.todo.api.model.Todo;
import br.com.todo.api.repository.filter.TodoFilter;
import br.com.todo.api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tarefas")
public class TodoResource {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> buscarTodos(TodoFilter todoFilter) {
        return todoService.buscarTodos(todoFilter);
    }

    @PostMapping
    public ResponseEntity<Todo> salvarTarefas(@Valid @RequestBody Todo todo) {
        Todo todoSalvo = todoService.salvar(todo);

        return ResponseEntity.status(HttpStatus.CREATED).body(todoSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerTarefa(@PathVariable Long codigo) {
        todoService.deletar(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Void> atualizarTarefa(@RequestBody Todo todo, @PathVariable Long codigo) {
        todo.setIdTodo(codigo);
        todoService.salvar(todo);

        return ResponseEntity.noContent().build();
    }
}
