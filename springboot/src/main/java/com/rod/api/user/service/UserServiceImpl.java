package com.rod.api.user.service;

import com.rod.api.article.model.Article;
import com.rod.api.article.repository.ArticleRepository;
import com.rod.api.common.component.JwtProvider;
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
    private final ArticleRepository articleRepository;
    private final JwtProvider jwtProvider;

    @Override
    public Messenger save(UserDto userDto) {
        entityToDto(userRepository.save(dtoToEntity(userDto)));

        return Messenger.builder()
                .message("SUCCESS")
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
        userRepository.deleteById(id);
        String msg = userRepository.findById(id).isPresent() ? "SUCCESS" : "FAILURE";

        return Messenger.builder()
                .message(msg)
                .build();
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
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return Optional.of(entityToDto(user));
        } else {
            log.warn("User with id '{}' not found.", id);

            return Optional.empty();
        }
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
    public Optional<UserDto> findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return Optional.of(entityToDto(user));
        } else {
            log.warn("User with email '{}' not found.", email);

            return Optional.empty();
        }
    }

    @Override
    public Messenger modify(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        if (optionalUser.isPresent()) {
            UserDto updateUser = userDto.toBuilder()
                    .password(userDto.getPassword())
                    .phone(userDto.getPhone())
                    .email(userDto.getEmail())
                    .build();
            List<Article> articles = updateUser.getArticles();
            if (articles != null) {
                articles.forEach(article -> {
                    Article updateArticle = article.toBuilder()
                            .writer(dtoToEntity(updateUser))
                            .build();
                    articleRepository.save(updateArticle);
                });
            }
            userRepository.save(dtoToEntity(updateUser));

            return Messenger.builder()
                    .message("update SUCCESS")
                    .build();
        } else {
            log.warn("User with ID '{}' not found.", userDto.getId());

            return Messenger.builder()
                    .message("update FAIL")
                    .build();
        }
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public Boolean existById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.isPresent();
    }

    @Override
    public Messenger login(UserDto userDto) {
        boolean flag = userRepository.findByUsername(userDto.getUsername()).get().getPassword().equals(userDto.getPassword());

        return Messenger.builder()
                .message(flag ? "SUCCESS" : "FAILURE")
                .token(flag ? jwtProvider.createToken(userDto) : "None")
                .build();

    }
}
