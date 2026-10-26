package ch.redsen.interview.domain.service;

import java.util.List;
import java.util.Optional;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import ch.redsen.interview.domain.model.User;
import ch.redsen.interview.domain.repository.EmailRepository;
import ch.redsen.interview.domain.repository.UserRepository;

@ApplicationScoped
public class UserService {
    @Inject
    private EmailRepository emailRepository;
    @Inject
    private UserRepository userRepository;

    @ConfigProperty(name = "fallback.user-email")
    private String fallbackUserEmail;

    public String getUserEmail(Long userId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
