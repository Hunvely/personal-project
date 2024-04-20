package com.rod.api.user;

import com.rod.api.common.component.Messenger;
import com.rod.api.user.model.UserDto;
import com.rod.api.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
@Slf4j
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping(path = "/save")
    public ResponseEntity<Messenger> save(@RequestBody UserDto userDto) {
        log.info("Received request to save user: {}", userDto);

        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<UserDto>> findAll() throws SQLException {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Messenger> deleteById(@RequestBody Long id) {
        log.info("Received request to delete user with ID: {}", id);

        return ResponseEntity.ok(userService.deleteById(id));
    }

    @GetMapping(path = "/search/username")
    public ResponseEntity<UserDto> findByUsername(@RequestParam String username) {
        log.info("Received request to find user with username: {}", username);

        Optional<UserDto> userDtoOptional = userService.findByUsername(username);

        return userDtoOptional.map(i -> ResponseEntity.ok(i)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/search/name")
    public ResponseEntity<List<UserDto>> findByName(@RequestParam String name) {
        log.info("Received request to find user with username: {}", name);

        return ResponseEntity.ok(userService.findByName(name));
    }

    @GetMapping(path = "/search/id")
    public ResponseEntity<UserDto> findById(@RequestParam Long id) {
        log.info("Received request to find user with id: {}", id);

        Optional<UserDto> userDtoOptional = userService.findById(id);

        return userDtoOptional.map(i -> ResponseEntity.ok(i)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/search/email")
    public ResponseEntity<UserDto> findByEmail(@RequestParam String email) {
        log.info("Received request to find user with email: {}", email);

        Optional<UserDto> userDtoOptional = userService.findByEmail(email);

        return userDtoOptional.map(i -> ResponseEntity.ok(i)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/modify")
    public ResponseEntity<Messenger> modify(@RequestParam UserDto userDto) {
        log.info("Received request to modify user: {}", userDto);

        return ResponseEntity.ok(userService.modify(userDto));
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(userService.count());
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Messenger> login(@RequestBody UserDto userDto) {
        log.info("입력받은 정보 : {}", userDto);

        return ResponseEntity.ok(userService.login(userDto));
    }

    @GetMapping(path = "exists-username")
    public ResponseEntity<Boolean> existsUsername(@RequestParam("username") String username) {
        log.info("existsUsername info : " + username);
        Boolean flag = userService.existsUsername(username);
        log.info("existsUsername result : " + username);

        return ResponseEntity.ok(flag);
    }
}
