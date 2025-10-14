package ch.redsen.interview.persistence;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import ch.redsen.interview.domain.model.User;
import ch.redsen.interview.domain.repository.UserRepository;

@Repository
public class InMemoryUserRepository implements UserRepository {


    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private long idCounter = 1;

    public InMemoryUserRepository() {
        // Seed with some data
        save(new User(null, "alice", "alice@email.com"));
        save(new User(null, "bob", "bob@email.com"));
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.values().stream().filter(u -> u.username().equals(username)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return new java.util.ArrayList<>(users.values());
    }

    @Override
    public User save(User user) {
        Long id = user.id() != null ? user.id() : idCounter++;
        User newUser = new User(id, user.username(), user.email());
        users.put(id, newUser);
        return newUser;
    }

    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }
}