package ch.redsen.interview.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ch.redsen.interview.domain.model.User;
import ch.redsen.interview.domain.repository.EmailRepository;
import ch.redsen.interview.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    EmailRepository emailRepository;

    @InjectMocks
    UserService userService;

    private final User user1 = new User(1L, "alice", "alice@email.com");
    private final User user2 = new User(2L, "bob", "bob@email.com");

    @BeforeEach
    void setUp() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(user1));
        when(userRepository.findByUsername("bob")).thenReturn(Optional.of(user2));
        when(userRepository.findByUsername("nobody")).thenReturn(Optional.empty());
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        when(userRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        doNothing().when(userRepository).deleteById(anyLong());
        when(emailRepository.getUserEmail(1L)).thenReturn(Optional.of("alice@email.com"));
    }

    @Test
    void testGetUserById() {
        assertEquals(user1, userService.getUserById(1L).orElse(null));
        assertEquals(user2, userService.getUserById(2L).orElse(null));
        assertTrue(userService.getUserById(99L).isEmpty());
    }

    @Test
    void testGetUserByUsername() {
        assertEquals(user1, userService.getUserByUsername("alice").orElse(null));
        assertEquals(user2, userService.getUserByUsername("bob").orElse(null));
        assertTrue(userService.getUserByUsername("nobody").isEmpty());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    void testCreateUser() {
        User newUser = new User(null, "carol", "carol@email.com");
        User saved = userService.createUser(newUser);
        assertEquals(newUser, saved);
        verify(userRepository).save(newUser);
    }

    @Test
    void testDeleteUser() {
        assertDoesNotThrow(() -> userService.deleteUser(1L));
        verify(userRepository).deleteById(1L);
    }

    @Test
    void testGetUserEmail() {
        String email = userService.getUserEmail(user1);
        verify(emailRepository).getUserEmail(1L);
    }
}
