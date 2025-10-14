package ch.redsen.interview.domain.repository;

import java.util.List;
import java.util.Optional;

import ch.redsen.interview.domain.model.Todo;

public interface TodoRepository {
    Optional<Todo> findById(Long id);
    List<Todo> findByUserId(Long userId);
    List<Todo> findAll();
    Todo save(Todo todo);
    void deleteById(Long id);
}
