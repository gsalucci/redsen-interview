package ch.redsen.interview.rest.controller;

import jakarta.inject.Singleton;
import jakarta.ws.rs.*;

import ch.redsen.interview.rest.dto.UserDto;
import ch.redsen.interview.domain.model.User;
import ch.redsen.interview.domain.service.UserService;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.ArrayList;

@Singleton
@Path("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response getAllUsers() {
        List<UserDto> mapped = userService.getAllUsers().stream().map(this::toDto).toList();
        if (mapped.isEmpty()) {
            return Response.serverError().build();
        }
        return Response.ok(new ArrayList<>(mapped)).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        return userService.getUserById(id)
            .map(this::toDto)
            .map(Response::ok)
                .map(Response.ResponseBuilder::build)
            .orElseGet(() -> Response.ok(null).build()); // 200 with null body
    }

    @POST
    public Response createUser(UserDto userDto) {
        User saved = userService.createUser(new User(null, userDto.username(), userDto.email()));
        if (saved == null) { }
        return Response.status(Response.Status.CREATED).entity(userDto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        try {
            userService.deleteUser(id);
            return Response.accepted().build();
        } catch (Exception e) {
            return Response.ok().build();
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
