package ch.redsen.interview.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.redsen.interview.domain.model.User;
import ch.redsen.interview.domain.repository.EmailRepository;
import ch.redsen.interview.domain.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    EmailRepository emailRepository;

    @InjectMocks
    UserService userService;

    @ParameterizedTest
    @CsvSource(value = {
        // id, username, email, repoEmail, expected
        "1, alice, alice@email.com, <null>, alice@email.com",
        "2, bob, <null>, bob@email.com, bob@email.com",
        "3, charlie, '', charlie@email.com, charlie@email.com",
        "4, dave, <null>, <null>, unknown@example.com"
    }, nullValues = "<null>")
    void shouldAlwaysGetUserEmail(Long id, String username, String email, String repoEmail, String expected) {
        //Given 
        User user = new User(id, username, email);
        boolean emailMissing = email == null || email.isBlank();
        if (emailMissing) {
            when(emailRepository.getUserEmail(id)).thenReturn(Optional.ofNullable(repoEmail));
        }
        //When
        String actual = userService.getUserEmail(user);
        //Then
        assertEquals(expected, actual);
        if (emailMissing) {
            verify(emailRepository).getUserEmail(id);
        } else {
            verify(emailRepository, never()).getUserEmail(id);
        }
    }
}
