package br.com.todo.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TODO")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long idTodo;

    @NotBlank(message = "Atenção! Digite a tarefa!")
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Boolean done;

    @Getter
    @Setter
    private LocalDateTime createdAt;

    public Todo() {
        this.createdAt = LocalDateTime.now();
        this.done = false;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Todo) ) return false;

        Todo todo = (Todo) o;

        return getIdTodo ( ) != null ? getIdTodo ( ).equals ( todo.getIdTodo ( ) ) : todo.getIdTodo ( ) == null;
    }

    @Override
    public int hashCode() {
        return getIdTodo ( ) != null ? getIdTodo ( ).hashCode ( ) : 0;
    }
}
