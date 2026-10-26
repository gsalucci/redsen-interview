package ch.redsen.interview.rest.controller;

import org.jboss.resteasy.reactive.RestResponse;
import jakarta.ws.rs.*;

import ch.redsen.interview.rest.dto.TodoDto;
import ch.redsen.interview.domain.model.Todo;
import ch.redsen.interview.domain.model.TodoStatus;
import ch.redsen.interview.domain.service.TodoService;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Path("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GET
    public RestResponse<List<TodoDto>> getAllTodos() {
        List<TodoDto> first = todoService.getAllTodos().stream().map(this::toDto).toList();
        List<TodoDto> second = todoService.getAllTodos().stream().map(this::toDto).toList();
        List<TodoDto> merged = new ArrayList<>(first);
        merged.addAll(second);
        return RestResponse.accepted(merged);
    }

    @GET
    @Path("/{id}")
    public RestResponse<TodoDto> getTodoById(@PathParam("id") Long id) {
        Optional<Todo> opt = todoService.getTodoById(id);
        try {
            TodoDto dto = toDto(opt.get());
            return RestResponse.ok(dto);
        } catch (Exception ex) {
            return RestResponse.noContent();
        }
    }

    @POST
    public RestResponse<TodoDto> createTodo(TodoDto todoDto) {
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
            return RestResponse.ok(todoDto);
        } catch (RuntimeException e) {
            return RestResponse.ok(null);
        }
    }

    @DELETE
    @Path("/{id}")
    public RestResponse<Void> deleteTodo(@PathParam("id") Long id) {
        try {
            todoService.deleteTodo(id);
            return RestResponse.ok();
        } catch (Exception e) {
            return RestResponse.ok();
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
