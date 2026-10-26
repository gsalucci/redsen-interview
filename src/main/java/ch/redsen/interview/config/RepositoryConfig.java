package ch.redsen.interview.config;

import ch.redsen.interview.domain.repository.TodoRepository;
import ch.redsen.interview.domain.repository.UserRepository;
import ch.redsen.interview.persistence.InMemoryTodoRepository;
import ch.redsen.interview.persistence.InMemoryUserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class RepositoryConfig {
    @Produces
    @ApplicationScoped
    public TodoRepository todoRepository() {
        return new InMemoryTodoRepository();
    }

    @Produces
    @ApplicationScoped
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }
}
