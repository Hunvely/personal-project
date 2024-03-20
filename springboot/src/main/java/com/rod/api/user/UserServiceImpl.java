package com.rod.api.user;

import com.rod.api.common.AbstractService;
import com.rod.api.enums.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends AbstractService implements UserService{

    private final UserRepository userRepository;
    private Map<String, User> users;


    @Override
    public Messenger save(User user) throws SQLException {
        users.put(user.getUsername(), user);
        userRepository.save(user);
        return Messenger.SUCCESS;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public String login(User user) {
        return users.getOrDefault(user.getUsername(), User.bulder().password("").build())
                .getPassword()
                .equals(user.getPassword()) ?
                user.getUsername() + "님 로그인 성공" : user.getUsername() + "님 로그인 실패";
    }

    @Override
    public String deleteAll() {
        return null;
    }

    @Override
    public Optional findById(Long id) {
        return Optional.of(users
                .values()
                .stream()
                .filter(i -> i.getUsername().equals(id))
                .collect(Collectors.toList()).get(0));
    }

    @Override
    public String updatePassword(User user) {
        users.get(user.getUsername()).setPassword(user.getPassword());

        return user.getName() + "님 비밀번호 변경 성공";
    }

    @Override
    public String delete(User user) {
        users.remove(user.getUsername());
        return "탈퇴 완료";
    }

    @Override
    public Boolean existsById(Long id) {
        return users.containsKey(id);
    }

    @Override
    public List<?> findUsersByName(String name) {
        return users
                .values()
                .stream()
                .filter(i -> i.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, ?> findUsersByNameFromMap(String name) {
        return users
                .entrySet()
                .stream()
                .filter(i -> i.getKey().equals(name))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                ;
    }

    @Override
    public List<?> findUsersByJob(String job) {

        users.values().stream().forEach(i -> System.out.println("직업 :" + i.getJob()));
        return users
                .values()
                .stream()
                .filter(i -> i.getJob().equals(job))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, ?> findUserByJobFromMap(String job) {
        return users
                .entrySet()
                .stream()
                .filter(i -> i.getKey().equals(job))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public String count() {
        return users.size() + "";
    }

    @Override
    public Optional getOne(String id) {
        return Optional.of(users.get(id));
    }

    @Override
    public String delete(Object o) {
        return null;
    }


    @Override
    public Map<String, ?> getUserMap() {
        return users;
    }


    @Override
    public Messenger createTable() throws SQLException {
        return userRepository.createTable();
    }

    @Override
    public Messenger dropTable() throws SQLException {
        return userRepository.dropTable();
    }

    @Override
    public List<?> findUsers() throws SQLException {
        return userRepository.findUsers();
    }
}
