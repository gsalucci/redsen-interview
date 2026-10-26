package ch.redsen.interview.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import ch.redsen.interview.client.UserEmailClient;
import ch.redsen.interview.domain.repository.EmailRepository;

@ApplicationScoped
public class ClientConfig {

    @Produces
    @ApplicationScoped
    public EmailRepository emailRepository(@ConfigProperty(name = "client.user-email.delay-ms", defaultValue = "1500") long delayMs) {
        return new UserEmailClient(delayMs);
    }
}
