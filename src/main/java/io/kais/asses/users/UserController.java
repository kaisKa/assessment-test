package io.kais.asses.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * This is a controller for the Crud endpoints
 * some of them are secured just for demo purpose and some are not
 * apply validation also
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    @Operation(description = "Endpoint to get all users ")
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<List<User>> listAll() {
        var users = this.service.getAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint to get a user by it id")
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(this.service.getById(id));
    }

    @PostMapping()
    @Operation(description = "Endpoint to create a new user")
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto user) {
        return ResponseEntity.created(URI.create("/api/users")).body(this.service.create(user));
    }

    @PutMapping()
    @Operation(description = "Endpoint to update existing user and create one in case does not exist")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user) {
        return ResponseEntity.ok().body(this.service.update(user));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint to delete a user from the db")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        this.service.delete(id);
        return new ResponseEntity<>(("User deleted - User ID:" + id), HttpStatus.OK);
    }
}
