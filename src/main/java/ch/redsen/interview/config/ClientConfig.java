package ch.redsen.interview.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.redsen.interview.client.UserEmailClient;
import ch.redsen.interview.domain.repository.EmailRepository;

@Configuration
public class ClientConfig {

    @Bean
    public EmailRepository emailRepository(@Value("${client.user-email.delay-ms:1500}") long delayMs) {
        return new UserEmailClient(delayMs);
    }
}
