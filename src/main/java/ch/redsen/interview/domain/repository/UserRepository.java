package ch.redsen.interview.domain.repository;

import java.util.List;
import java.util.Optional;

import ch.redsen.interview.domain.model.User;

public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    User save(User user);
    void deleteById(Long id);
}
