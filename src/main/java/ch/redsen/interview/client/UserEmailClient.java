package ch.redsen.interview.client;

import java.util.Map;
import java.util.Optional;

import ch.redsen.interview.domain.repository.EmailRepository;

/**
 * Simulates a long-running REST call to fetch a user's email address.
 * A configurable delay is applied on every invocation to mimic network latency.
 */
public class UserEmailClient implements EmailRepository {

    private final long delayMs;

    public UserEmailClient(long delayMs) {
        this.delayMs = Math.max(0, delayMs);
    }

    Map<Long, String> userEmails = Map.of(
            1L, "user1@example.com",
            2L, "user2@example.com",
            3L, "user3@example.com");

    @Override
    public Optional<String> getUserEmail(Long userId) {
        // Simulate a slow network call
        if (delayMs > 0) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException ie) {
                // Restore interrupt flag and proceed without failing hard
                Thread.currentThread().interrupt();
            }
        }
        return Optional.ofNullable(userEmails.get(userId));
    }

}
