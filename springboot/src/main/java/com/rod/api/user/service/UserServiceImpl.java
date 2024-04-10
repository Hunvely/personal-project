package com.rod.api.user.service;

import com.rod.api.common.component.Messenger;
import com.rod.api.user.model.User;
import com.rod.api.user.model.UserDto;
import com.rod.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Messenger save(UserDto userDto) {
        entityToDto(userRepository.save(dtoToEntity(userDto)));
        return new Messenger();
    }

    @Override
    public Messenger deleteById(Long id) {
        userRepository.deleteById(id);
        return new Messenger();
    }

    @Override
    public List<UserDto> findAll() throws SQLException {
        return userRepository.findAll().stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return Optional.of(entityToDto(user));
        } else {
            log.warn("User with username '{}' not found.", username);
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserDto> findByName(String name) {
        List<User> user = userRepository.findByName(name);
        if (user != null) {
            return user.stream().map(i -> entityToDto(i)).toList();
        } else {
            log.warn("No users found with the name: {}", name);

            return Collections.emptyList(); // 빈 리스트 반환 -> NullPointException 방지 가능
        }
    }

    @Override
    public UserDto findByEmail(String email) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Boolean existById(Long id) {
        return null;
    }

    @Override
    public Messenger modify(UserDto userDto) {
        return null;
    }

    @Override
    public Messenger login(UserDto param) {
        return null;
    }
}
