package ch.redsen.interview;

import static org.assertj.core.api.Assertions.assertThat;

import ch.redsen.interview.domain.service.TodoService;
import ch.redsen.interview.domain.service.UserService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class InterviewApplicationTests {

	@Inject
	TodoService todoService;

	@Inject
	UserService userService;

	@Test
	void contextLoads() {
		assertThat(todoService).isNotNull();
		assertThat(userService).isNotNull();
	}

}
