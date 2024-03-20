package com.rod.api.user;

import com.rod.api.enums.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Controller는 Springframework에서 사용
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public Map<String, ?> login(@RequestBody Map<?, ?> map) {
        String username = (String) map.get("username");
        System.out.println("리퀘스트가 가져온 이름 : " + username);
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("username", "안녕" + username);
        return reqMap;
    }

    public Messenger save(Scanner input) throws SQLException {
        userServiceImpl.save(User.bulder()
                .username(input.next())
                .password(input.next())
                .name(input.next())
                .phoneNumber(input.next())
                .job(input.next())
                .height(input.next())
                .weight(input.next())
                .build());

        return Messenger.SUCCESS;
    }

    public String login(Scanner input) {
        return userServiceImpl.login(User.bulder()
                .username(input.next())
                .password(input.next())
                .build());
    }

    public Optional<User> findUserById(Scanner input) {
        return userServiceImpl.findById(Long.parseLong(input.next()));
    }

    public String updatePassword(Scanner input) {
        return userServiceImpl.updatePassword(User.bulder()
                .username(input.next())
                .password(input.next())
                .build());
    }

    public String delete(Scanner input) {
        userServiceImpl.delete(User.bulder()
                .username(input.next())
                .build());
        return "회원 탈퇴 완료.";
    }

    public Boolean existsById(Scanner scanner) {
        return userServiceImpl.existsById(Long.parseLong(scanner.next()));
    }

    public List<?> findUsersByName(Scanner input) {
        return userServiceImpl.findUsersByName(input.next());
    }

    public Map<String, ?> findUsersByNameFromMap(Scanner input) {
        return userServiceImpl.findUsersByNameFromMap(input.next());
    }

    public List<?> findUsersByJob(Scanner input) {
        System.out.println("직업으로 검색");
        return userServiceImpl.findUsersByJob(input.next());
    }

    public Map<String, ?> findUsersByJobFromMap(Scanner input) {
        return userServiceImpl.findUserByJobFromMap(input.next());
    }

    public String count() {
        return userServiceImpl.count();
    }

    public Optional<User> getOne(Scanner input) {
        return userServiceImpl.getOne(input.next());
    }

    public Map<String, ?> getUserMap() {
        return userServiceImpl.getUserMap();
    }

    public List<?> findUsers() throws SQLException {
        return userServiceImpl.findUsers();
    }

    public Messenger createTable() throws SQLException {
        return userServiceImpl.createTable();
    }

    public Messenger dropTable() throws SQLException {
        return userServiceImpl.dropTable();
    }

}
