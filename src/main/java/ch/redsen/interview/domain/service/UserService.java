package ch.redsen.interview.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.redsen.interview.domain.model.User;
import ch.redsen.interview.domain.repository.EmailRepository;

@Service
public class UserService {
    @Autowired
    private EmailRepository emailRepository;

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

    public String getUserEmailFromEmailRepository(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserEmail'");
    }
}
