package ch.redsen.interview.domain.service;

import java.util.List;
import java.util.Optional;

import ch.redsen.interview.domain.model.User;

public class UserService {
    public Optional<User> getUserById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    public Optional<User> getUserByUsername(String username) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserByUsername'");
    }

    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    public User createUser(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    public void deleteUser(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    public String getUserEmail(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserEmail'");
    }
}
