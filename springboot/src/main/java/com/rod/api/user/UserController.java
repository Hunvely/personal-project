package com.rod.api.user;

import com.rod.api.enums.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") // http://localhost:3000 에서 온 AJAX요청만 받아주겠다는 의미.
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @PostMapping(path = "/api/users")
    public Map<String, ?> join(@RequestBody Map<?, ?> requestBody) { // @RequestBody : 요청으로 넘어온 body 값들을 자바 타입으로 변환하겠다는 의미.

        Map<String, Messenger> respMap = new HashMap<>();
        User user = userRepository.save(User.builder()
                .username((String) requestBody.get("username"))
                .password((String) requestBody.get("password"))
                .name((String) requestBody.get("name"))
                .eMail((String) requestBody.get("eMail"))
                .phoneNumber((String) requestBody.get("phoneNumber"))
                .build());
        System.out.println("DB 에 저장된 User 정보 : " + user);
        respMap.put("result", Messenger.SUCCESS);

        return respMap;
    }

    @PostMapping("/api/auth")
    public Map<String, ?> login(@RequestBody Map<?, ?> requestBody) { // @RequestBody : 프론트에서 요청한 데이터가 Map에 담긴다.
        Map<String, Messenger> respMap = new HashMap<>();

        String username = (String) requestBody.get("username"); // 구현할 때 map의 타입을 가져와야 함.
        String password = (String) requestBody.get("password");

        User optUser = userRepository.findByUsername((String) requestBody.get("username")).orElse(null);
        System.out.println("User is " + null);

        if (optUser == null) {
            respMap.put("message", Messenger.FAIL);
        } else if (!optUser.getPassword().equals(password)) {
            System.out.println("ID is " + username);
            respMap.put("message", Messenger.WRONG_PASSWORD);
        } else {
            System.out.println("ID is " + username);
            password = optUser.getPassword();
            System.out.println("Password is " + password);
            respMap.put("message", Messenger.SUCCESS);
        }

        return respMap;
    }

    @PutMapping("/api/updateUserInfo")
    public Map<String, ?> updateUser(@RequestBody Map<?, ?> requestBody) {
        Map<String, Messenger> respMap = new HashMap<>();

        return respMap;
    }

    @DeleteMapping("api/withdrawal/{username}")
    public Map<String, ?> withdrawal(@PathVariable String username, @RequestBody Map<?, ?> requestBody) {
        Map<String, Messenger> respMap = new HashMap<>();

        String password = (String)requestBody.get("password"); // 요청 본문에서 비밀번호 추출

        User optUser = userRepository.findByUsername(username).orElse(null);

        if (optUser == null) {
            respMap.put("message", Messenger.FAIL);
        } else if (!optUser.getPassword().equals(password)) {
            respMap.put("message", Messenger.WRONG_PASSWORD);
        } else {
            userRepository.deleteByUsername(username);
            respMap.put("message", Messenger.SUCCESS);
        }

        return respMap;
    }
}
