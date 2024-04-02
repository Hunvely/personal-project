package com.rod.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);

//    @Modifying
//    @Query("DELETE FROM User u WHERE u.username = :username")
//    void deleteByUsername(String username);
}
