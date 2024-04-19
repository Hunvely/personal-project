package com.rod.api.user.repository;

import com.rod.api.user.model.User;
import com.rod.api.user.model.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByName(String name);

    Optional<User> findByEmail(String email);

    @Modifying // 상태 변화 시킬 때 사용
    @Query("update User u set u.token = :token where u.id = :id")
    void modifyTokenById(@Param("id") Long id, @Param("token") String token);
}
