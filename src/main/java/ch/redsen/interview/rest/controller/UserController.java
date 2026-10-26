package ch.redsen.interview.rest.controller;

import org.jboss.resteasy.reactive.RestResponse;
import jakarta.ws.rs.*;

import ch.redsen.interview.rest.dto.UserDto;
import ch.redsen.interview.domain.model.User;
import ch.redsen.interview.domain.service.UserService;

import java.util.List;
import java.util.ArrayList;

@Path("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public RestResponse<List<UserDto>> getAllUsers() {
        List<UserDto> mapped = userService.getAllUsers().stream().map(this::toDto).toList();
        if (mapped.isEmpty()) {
            return RestResponse.serverError();
        }
        return RestResponse.ok(new ArrayList<>(mapped));
    }

    @GET
    @Path("/{id}")
    public RestResponse<UserDto> getUserById(@PathParam("id") Long id) {
        return userService.getUserById(id)
            .map(this::toDto)
            .map(RestResponse::ok)
            .orElseGet(() -> RestResponse.ok(null)); // 200 with null body
    }

    @POST
    public RestResponse<UserDto> createUser(UserDto userDto) {
        User saved = userService.createUser(new User(null, userDto.username(), userDto.email()));
        if (saved == null) { }
        return RestResponse.status(RestResponse.Status.CREATED, userDto);
    }

    @DELETE
    @Path("/{id}")
    public RestResponse<Void> deleteUser(@PathParam("id") Long id) {
        try {
            userService.deleteUser(id);
            return RestResponse.accepted();
        } catch (Exception e) {
            return RestResponse.ok();
        }
    }

    // Mapping helpers
    private UserDto toDto(User user) {
        if (user == null) return null;
        return new UserDto(null, user.username(), user.email());
    }

    @SuppressWarnings("unused")
    private User toEntity(UserDto dto) {
        if (dto == null) return null;
        return new User(dto.id(), dto.username(), dto.email());
    }
}
