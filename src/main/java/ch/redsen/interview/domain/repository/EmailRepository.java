package ch.redsen.interview.domain.repository;

import java.util.Optional;

public interface EmailRepository {
    Optional<String> getUserEmail(Long userId);
}
