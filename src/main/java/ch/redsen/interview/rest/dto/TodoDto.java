package ch.redsen.interview.rest.dto;

import java.time.LocalDateTime;

import ch.redsen.interview.domain.model.TodoStatus;

public record TodoDto(
        Long id,
        String title,
        String description,
        TodoStatus status,
        LocalDateTime dueDate,
        Long userId) {
}
