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
    public Map<String, ?> join(@RequestBody Map<?, ?> map) { // @RequestBody : 요청으로 넘어온 body 값들을 자바 타입으로 변환하겠다는 의미.

        Map<String, Messenger> respMap = new HashMap<>();
        User user = userRepository.save(User.builder()
                .username((String) map.get("username"))
                .password((String) map.get("password"))
                .name((String) map.get("name"))
                .eMail((String) map.get("eMail"))
                .phoneNumber((String) map.get("phoneNumber"))
                .build());
        System.out.println("DB 에 저장된 User 정보 : " + user);
        respMap.put("result", Messenger.SUCCESS);

        return respMap;
    }

    @PostMapping("/api/login")
    public Map<String, ?> login(@RequestBody Map<?, ?> map) { // @RequestBody : 프론트에서 요청한 데이터가 map에 담긴다.
        Map<String, Messenger> respMap = new HashMap<>();
        String username = (String)map.get("username"); // 구현할 때 map의 타입을 가져와야 함.
        String password = (String)map.get("password");
        User optUser = userRepository.findByUsername((String) map.get("username")).orElse(null);
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
    public Map<String, ?> updateUser(@RequestBody Map<?,?> map) {
        Map<String,Messenger> respMap = new HashMap<>();

        return respMap;
    }
}
