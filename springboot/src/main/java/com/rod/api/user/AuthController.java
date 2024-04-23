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
public class AuthController {

    private final UserServiceImpl userService;

    @PostMapping(path = "/login")
    public ResponseEntity<Messenger> login(@RequestBody UserDto userDto) {
        log.info("입력받은 정보 : {}", userDto.getUsername());
        log.info("입력받은 정보 : {}", userDto.getPassword());

        return ResponseEntity.ok(userService.login(userDto));
    }

    @GetMapping("/exists-username")
    public ResponseEntity<Boolean> existsUsername(@RequestParam("username") String username) {
        log.info("existsUsernam 파라미터 정보 : " + username);
        Boolean flag = userService.existsUsername(username);
        log.info("existsUsernam 결과 : " + flag);

        return ResponseEntity.ok(flag);
    }
}
