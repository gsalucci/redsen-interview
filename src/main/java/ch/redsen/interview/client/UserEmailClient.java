package ch.redsen.interview.client;

import java.util.Map;
import java.util.Optional;

import ch.redsen.interview.domain.repository.EmailRepository;

public class UserEmailClient implements EmailRepository {

    Map<Long, String> userEmails = Map.of(
            1L, "user1@example.com",
            2L, "user2@example.com",
            3L, "user3@example.com");

    @Override
    public Optional<String> getUserEmail(Long userId) {
        return Optional.ofNullable(userEmails.get(userId));
    }

}
