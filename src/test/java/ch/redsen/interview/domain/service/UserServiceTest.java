package ch.redsen.interview.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;

import ch.redsen.interview.domain.model.User;
import ch.redsen.interview.domain.repository.EmailRepository;
import ch.redsen.interview.domain.repository.UserRepository;

@QuarkusTest
@TestProfile(UserServiceTest.FallbackEmailProfile.class)
public class UserServiceTest {

    public static class FallbackEmailProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Map.of("fallback.user-email", "unknown@example.com");
        }
    }

    @Inject
    private UserService userService;

    @InjectMock
    private EmailRepository emailRepository;

    @InjectMock
    private UserRepository userRepository;

    @ParameterizedTest
    @CsvSource(value = {
            "1, alice, alice@email.com, <null>, alice@email.com",
            "2, bob, <null>, bob@email.com, bob@email.com",
            "3, charlie, '', charlie@email.com, charlie@email.com",
            "4, dave, <null>, <null>, unknown@example.com"
    }, nullValues = "<null>")
    void shouldAlwaysGetUserEmail(Long id, String username, String email, String repoEmail, String expected) {
        // Given
        boolean emailMissing = email == null || email.isBlank();
        when(userRepository.findById(id)).thenReturn(Optional.of(new User(id, username, email)));
        if (emailMissing) {
            when(emailRepository.getUserEmail(id)).thenReturn(Optional.ofNullable(repoEmail));
        }
        // When
        String actual = userService.getUserEmail(id);
        // Then
        assertEquals(expected, actual);
        if (emailMissing) {
            verify(emailRepository).getUserEmail(id);
        } else {
            verify(emailRepository, never()).getUserEmail(id);
        }
    }
}
