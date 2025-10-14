package ch.redsen.interview.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import ch.redsen.interview.domain.model.Todo;
import ch.redsen.interview.domain.model.TodoStatus;
import ch.redsen.interview.domain.repository.TodoRepository;

@Repository
public class InMemoryTodoRepository implements TodoRepository {


    private final Map<Long, Todo> todos = new ConcurrentHashMap<>();
    private long idCounter = 1;

    public InMemoryTodoRepository() {
        save(new Todo(null, "Task 1", "desc1", TodoStatus.PENDING, LocalDateTime.now().plusDays(1), 1L));
        save(new Todo(null, "Task 2", "desc2", TodoStatus.IN_PROGRESS, LocalDateTime.now().plusDays(2), 1L));
        save(new Todo(null, "Task 3", "desc3", TodoStatus.COMPLETED, LocalDateTime.now().plusDays(3), 2L));
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(todos.get(id));
    }

    @Override
    public List<Todo> findByUserId(Long userId) {
        return todos.values().stream().filter(t -> t.userId().equals(userId)).toList();
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(todos.values());
    }

    @Override
    public Todo save(Todo todo) {
        Long id = todo.id() != null ? todo.id() : idCounter++;
        Todo newTodo = new Todo(id, todo.title(), todo.description(), todo.status(), todo.dueDate(), todo.userId());
        todos.put(id, newTodo);
        return newTodo;
    }

    @Override
    public void deleteById(Long id) {
        todos.remove(id);
    }

}