package ch.redsen.interview.domain.model;

import java.time.LocalDateTime;

public record Todo(
    Long id,
    String title,
    String description,
    TodoStatus status,
    LocalDateTime dueDate,
    Long userId
) {}
