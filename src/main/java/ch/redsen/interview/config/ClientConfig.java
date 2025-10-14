package ch.redsen.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.redsen.interview.client.UserEmailClient;
import ch.redsen.interview.domain.repository.EmailRepository;

@Configuration
public class ClientConfig {
    @Bean
    public EmailRepository emailRepository() {
        return new UserEmailClient();
    }
}
