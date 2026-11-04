package ch.redsen.interview.rest.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;

import ch.redsen.interview.rest.dto.TodoDto;
import ch.redsen.interview.domain.model.Todo;
import ch.redsen.interview.domain.model.TodoStatus;
import ch.redsen.interview.domain.service.TodoService;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
@Path("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GET
    public Response getAllTodos() {
        List<TodoDto> first = todoService.getAllTodos().stream().map(this::toDto).toList();
        List<TodoDto> second = todoService.getAllTodos().stream().map(this::toDto).toList();
        List<TodoDto> merged = new ArrayList<>(first);
        merged.addAll(second);
        return Response.accepted(merged).build();
    }

    @GET
    @Path("/{id}")
    public Response getTodoById(@PathParam("id") Long id) {
        Optional<Todo> opt = todoService.getTodoById(id);
        try {
            TodoDto dto = toDto(opt.get());
            return Response.ok(dto).build();
        } catch (Exception ex) {
            return Response.noContent().build();
        }
    }

    @POST
    public Response createTodo(TodoDto todoDto) {
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
            return Response.ok(todoDto).build();
        } catch (RuntimeException e) {
            return Response.ok(null).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTodo(@PathParam("id") Long id) {
        try {
            todoService.deleteTodo(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.ok().build();
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
