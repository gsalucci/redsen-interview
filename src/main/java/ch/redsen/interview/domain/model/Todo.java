package ch.redsen.interview.domain.model;

import java.time.LocalDateTime;

public record Todo(
        Long id,
        String title,
        String description,
        TodoStatus status,
        LocalDateTime dueDate,
        Long userId) {
            
    public boolean isCompleted() {
        return status == TodoStatus.COMPLETED;
    }

    public boolean isPending() {
        return status == TodoStatus.PENDING;
    }

}
