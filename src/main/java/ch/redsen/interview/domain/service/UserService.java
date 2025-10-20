package ch.redsen.interview.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.redsen.interview.domain.model.User;
import ch.redsen.interview.domain.repository.EmailRepository;
import ch.redsen.interview.domain.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private UserRepository userRepository;

    public String getUserEmail(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserEmail'");
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
