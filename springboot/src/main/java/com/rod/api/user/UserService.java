package com.rod.api.user;

import com.rod.api.enums.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UserService {

     Messenger save(User user) throws SQLException;
    String login(User user);

    String updatePassword(User user);

    String delete(User user);

    List<?> findUsersByName(String name);

    Map<String, ?> findUsersByNameFromMap(String name);

    List<?> findUsersByJob(String job);

    Map<String, ?> findUserByJobFromMap(String name);

    Map<String, ?> getUserMap();

    List<?> findUsers() throws SQLException;

    Messenger createTable() throws SQLException;

    Messenger dropTable() throws SQLException;

}
