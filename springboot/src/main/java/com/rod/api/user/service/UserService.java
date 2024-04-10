package com.rod.api.user.service;

import com.rod.api.common.component.Messenger;
import com.rod.api.common.service.CommandService;
import com.rod.api.common.service.QueryService;
import com.rod.api.user.model.User;
import com.rod.api.user.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService extends CommandService<UserDto>, QueryService<UserDto> {

    Messenger modify(UserDto userDto);

    Optional<UserDto> findByUsername(String username);

    List<UserDto> findByName(String name);

    UserDto findByEmail(String email);

    Messenger login(UserDto param);

    default User dtoToEntity(UserDto userDto) {
        // DTO -> Entity, Entity는 DB와 매핑되는 객체이기 때문에 Service에서 Repository로 데이터 전달할 때 변환
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .build();
    }

    default UserDto entityToDto(User user) {
        // Entity -> DTO, DTO는 클라이언트와 서버 간의 데이터 전송을 위한 객체이기 때문에 클라이언트와 통신을 담당하는 Controller에서 사용하고 Service와 데이터 전달할 때도 사용
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }
}
