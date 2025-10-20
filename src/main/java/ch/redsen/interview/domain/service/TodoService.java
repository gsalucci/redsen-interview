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
    
    public Map<Long, List<Todo>> getTodosGroupedByUser() {
        throw new UnsupportedOperationException("Unimplemented method 'getTodosGroupedByUser'");
    }
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }
    public List<Todo> getTodosByUser(Long userId) {
        return todoRepository.findByUserId(userId);
    }
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
