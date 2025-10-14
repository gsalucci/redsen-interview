package ch.redsen.interview.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import ch.redsen.interview.domain.model.Todo;
import ch.redsen.interview.domain.model.TodoStatus;
import ch.redsen.interview.domain.repository.TodoRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;
    @InjectMocks
    private TodoService todoService;

    private final Todo todo1 = new Todo(1L, "Task 1", "desc1", TodoStatus.PENDING, LocalDateTime.now().plusDays(1), 1L);
    private final Todo todo2 = new Todo(2L, "Task 2", "desc2", TodoStatus.IN_PROGRESS, LocalDateTime.now().plusDays(2),
            1L);
    private final Todo todo3 = new Todo(3L, "Task 3", "desc3", TodoStatus.COMPLETED, LocalDateTime.now().plusDays(3),
            2L);
    private final Todo todo4 = new Todo(4L, "Task 4", "desc4", TodoStatus.PENDING, LocalDateTime.now().plusDays(4), 2L);
    private final List<Todo> allTodos = List.of(todo1, todo2, todo3, todo4);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mock repository methods
        Mockito.when(todoRepository.findAll()).thenReturn(allTodos);
        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.of(todo1));
        Mockito.when(todoRepository.findById(2L)).thenReturn(Optional.of(todo2));
        Mockito.when(todoRepository.findById(3L)).thenReturn(Optional.of(todo3));
        Mockito.when(todoRepository.findById(4L)).thenReturn(Optional.of(todo4));
        Mockito.when(todoRepository.findById(Mockito.longThat(id -> id < 1 || id > 4))).thenReturn(Optional.empty());
        Mockito.when(todoRepository.findByUserId(1L)).thenReturn(List.of(todo1, todo2));
        Mockito.when(todoRepository.findByUserId(2L)).thenReturn(List.of(todo3, todo4));
        Mockito.when(todoRepository.findByUserId(Mockito.longThat(id -> id < 1 || id > 2))).thenReturn(List.of());
        Mockito.when(todoRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testGetAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        assertEquals(4, todos.size());
        assertTrue(todos.contains(todo1));
        assertTrue(todos.contains(todo4));
    }

    @Test
    void testGetTodoById() {
        assertEquals(todo1, todoService.getTodoById(1L).orElse(null));
        assertEquals(todo4, todoService.getTodoById(4L).orElse(null));
        assertTrue(todoService.getTodoById(99L).isEmpty());
    }

    @Test
    void testGetTodosByUser() {
        List<Todo> user1Todos = todoService.getTodosByUser(1L);
        assertEquals(2, user1Todos.size());
        assertTrue(user1Todos.contains(todo1));
        assertTrue(user1Todos.contains(todo2));
        List<Todo> user2Todos = todoService.getTodosByUser(2L);
        assertEquals(2, user2Todos.size());
        assertTrue(user2Todos.contains(todo3));
        assertTrue(user2Todos.contains(todo4));
        assertTrue(todoService.getTodosByUser(99L).isEmpty());
    }

    @Test
    void testCreateTodo() {
        Todo newTodo = new Todo(null, "New Task", "desc", TodoStatus.PENDING, LocalDateTime.now(), 1L);
        Todo saved = todoService.createTodo(newTodo);
        assertEquals(newTodo, saved);
    }

    @Test
    void testDeleteTodo() {
        // Just ensure no exception is thrown
        assertDoesNotThrow(() -> todoService.deleteTodo(1L));
    }

    @Test
    void testGetTodosGroupedByUser() {
        Map<Long, List<Todo>> grouped = todoService.getTodosGroupedByUser();
        assertEquals(2, grouped.size());
        assertEquals(2, grouped.get(1L).size());
        assertEquals(2, grouped.get(2L).size());
        assertTrue(grouped.get(1L).contains(todo1));
        assertTrue(grouped.get(2L).contains(todo3));
    }
}
