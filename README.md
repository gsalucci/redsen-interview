# Redsen Interview Project

A small Spring Boot REST application used for a technical interview exercise. It models simple User and Todo management with in-memory persistence and unit tests that define the expected behavior.

## Tech stack

- Java 21
- Spring Boot 3.5.6
  - spring-boot-starter
  - spring-boot-starter-web (REST controllers)
- Jakarta Validation API
- JUnit + Mockito (tests)
- Maven (wrapper included)

See `pom.xml` for exact versions and dependencies.

## Architecture and design patterns
TODO: Let's discuss about this.

## What you need to implement (tests will guide you)

The unit tests in `src/test/java/ch/redsen/interview/domain/service` define the expected behavior. To complete the exercise you must:

- Implement the methods in the application services:
  - `domain/service/UserService.java`
  - `domain/service/TodoService.java`
- Do not change the tests. Make your implementations satisfy the tests.
- Use the repository ports in `domain/repository` and the in-memory adapters in `persistence` as needed. The wiring in `config/RepositoryConfig.java` should already provide beans.
- Keep business rules in the service layer and avoid leaking HTTP concerns into the domain.

Tip: Start by reading `UserServiceTest` and `TodoServiceTest` to understand the required service behavior and edge cases.

## How requests flow

HTTP request -> Controller (`rest/controller`) -> Service (`domain/service`) -> Repositories/Clients (`domain/repository` interfaces, implemented by `persistence`/`client`) -> Service returns result -> Controller maps to DTO and responds.

## Running locally

From a PowerShell terminal on Windows, you can use the Maven Wrapper included in the repo:

```powershell
# Run unit tests
.\mvnw.cmd -q -DskipTests=false test

# Build the application (runs tests by default)
.\mvnw.cmd -q clean package

# Start the application
.\mvnw.cmd -q spring-boot:run
```

The app will start on the configured port (see `src/main/resources/application.properties`).

## Running and iterating on tests

- Red-Green-Refactor is encouraged: run tests, implement service logic, and re-run until green.
- Focus on the service tests first:
  - `TodoServiceTest.java`
  - `UserServiceTest.java`

```powershell
# Run only tests under domain/service
.\mvnw.cmd -q -Dtest="*ServiceTest" test
```

## Notes

- There is no external database by default; data is lost on application restart.

## Acceptance criteria

- All unit tests pass after you implement the service methods.
- Public APIs and wiring remain consistent with the tests’ expectations.

Happy coding!