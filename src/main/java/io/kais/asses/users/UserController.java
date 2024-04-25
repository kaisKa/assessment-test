package io.kais.asses.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(this.service.getById(id));
    }

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        return ResponseEntity.created(URI.create("/api/users")).body(this.service.create(user));
    }

    @PutMapping()
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok().body(this.service.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        this.service.delete(id);
        return new ResponseEntity<>(("User deleted - User ID:" + id), HttpStatus.OK);
    }
}
