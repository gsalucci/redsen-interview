package ch.redsen.interview.config;

import ch.redsen.interview.domain.repository.TodoRepository;
import ch.redsen.interview.domain.repository.UserRepository;
import ch.redsen.interview.persistence.InMemoryTodoRepository;
import ch.redsen.interview.persistence.InMemoryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public TodoRepository todoRepository() {
        return new InMemoryTodoRepository();
    }

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }
}
