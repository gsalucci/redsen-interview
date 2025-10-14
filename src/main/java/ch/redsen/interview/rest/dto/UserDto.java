package ch.redsen.interview.rest.dto;

public record UserDto(
    Long id,
    String username,
    String email
) {}
