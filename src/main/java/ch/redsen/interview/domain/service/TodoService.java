package ch.redsen.interview.domain.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.redsen.interview.domain.model.Todo;
import ch.redsen.interview.domain.repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    
    public Optional<Todo> getTodoById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getTodoById'");
    }
    public List<Todo> getTodosByUser(Long userId) {
        throw new UnsupportedOperationException("Unimplemented method 'getTodosByUser'");
    }
    public List<Todo> getAllTodos() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllTodos'");
    }
    public Todo createTodo(Todo todo) {
        throw new UnsupportedOperationException("Unimplemented method 'createTodo'");
    }
    public void deleteTodo(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteTodo'");
    }
    public Map<Long, List<Todo>> getTodosGroupedByUser() {
        throw new UnsupportedOperationException("Unimplemented method 'getTodosGroupedByUser'");
    }
}
