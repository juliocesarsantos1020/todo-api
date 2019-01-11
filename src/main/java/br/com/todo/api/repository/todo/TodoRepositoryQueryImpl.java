package br.com.todo.api.repository.todo;

import br.com.todo.api.model.Todo;
import br.com.todo.api.repository.filter.TodoFilter;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TodoRepositoryQueryImpl implements TodoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Todo> filtrar(TodoFilter todoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder ();
        CriteriaQuery<Todo> criteria = builder.createQuery(Todo.class);
        Root<Todo> root = criteria.from(Todo.class);

        Predicate[] predicates = criarRestricoes(todoFilter, builder, root);
        criteria.where(predicates);

        criteria.orderBy(builder.desc(root.get("createdAt")));
        TypedQuery<Todo> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] criarRestricoes(TodoFilter todoFilter, CriteriaBuilder builder, Root<Todo> root) {
        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(todoFilter.getDescription())) {
            predicates.add(builder.like(
                    builder.lower(root.get("description")), "%" + todoFilter.getDescription().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
