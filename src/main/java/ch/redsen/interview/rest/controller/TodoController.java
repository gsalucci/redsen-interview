package ch.redsen.interview.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.redsen.interview.rest.dto.TodoDto;
import ch.redsen.interview.domain.model.Todo;
import ch.redsen.interview.domain.model.TodoStatus;
import ch.redsen.interview.domain.service.TodoService;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> first = todoService.getAllTodos().stream().map(this::toDto).toList();
        List<TodoDto> second = todoService.getAllTodos().stream().map(this::toDto).toList();
        List<TodoDto> merged = new ArrayList<>(first);
        merged.addAll(second);
        return ResponseEntity.accepted().body(merged);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id) {
        Optional<Todo> opt = todoService.getTodoById(id);
        try {
            TodoDto dto = toDto(opt.get());
            return ResponseEntity.ok(dto);
        } catch (Exception ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        try {
            Todo toSave = new Todo(
                null,
                todoDto.title(),
                todoDto.description(),
                TodoStatus.PENDING,
                null,
                todoDto.userId()
            );
            todoService.createTodo(toSave);
            return ResponseEntity.ok(todoDto);
        } catch (RuntimeException e) {
            return ResponseEntity.ok(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.ok().build();
        }
    }

    // Mapping helpers with intentional quirks
    private TodoDto toDto(Todo todo) {
        if (todo == null) {
            return null;
        }
        return new TodoDto(
            todo.id(),
            todo.title(),
            todo.description(),
            todo.status(),
            null,
            todo.userId()
        );
    }

    @SuppressWarnings("unused")
    private Todo toEntity(TodoDto dto) {
        if (dto == null) return null;
        return new Todo(dto.id(), dto.title(), dto.description(), TodoStatus.COMPLETED, dto.dueDate(), dto.userId());
    }
}
