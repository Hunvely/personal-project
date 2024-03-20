package com.rod.api.user;

import com.rod.api.enums.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;


@Repository
public class UserRepository {

    private Connection connection;

    private UserRepository() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/roddb",
                "roddb",
                "roddb"
        );
    }

    public List<?> findUsers() throws SQLException {

        String sql = "select * from users";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            do {
                System.out.printf("ID : %s\t Username : %s\t Password : %s\t Name : %s\t Phone_number : %s\t Job : %s\t Height : %s\t Weight : %s\n",
                        rs.getLong("id"),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                System.out.println();
            } while (rs.next());
        } else {
            System.out.println("데이터가 없습니다.");
        }
        rs.close();
        pstmt.close();
        connection.close();

        return null;
    }

    public Messenger createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users(                               \n" +
                "  id INT NOT NULL AUTO_INCREMENT,                 \n" +
                "  username VARCHAR(20) NOT NULL,                          \n" +
                "  password VARCHAR(20) NOT NULL,                      \n" +
                "  name  VARCHAR(30) NOT NULL, \n" +
                "  phone_number VARCHAR(20),\n" +
                "  job VARCHAR(20),\n" +
                "  height VARCHAR(20),\n" +
                "  weight VARCHAR(20),                                     \n" +
                "  CONSTRAINT users_pk PRIMARY KEY(id)             \n" +
                ");";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        int ex = pstmt.executeUpdate();
        pstmt.close();

        return (ex == 0) ? Messenger.SUCCESS : Messenger.FAIL;
    }

    public Messenger dropTable() throws SQLException {
        String sql = "drop table users;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.executeUpdate();

        return Messenger.SUCCESS;
    }

    public Messenger save(User user) throws SQLException {
        String sql = "INSERT INTO users(username,password,name,phone_number,job,height,weight)\n" +
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getPhoneNumber());
        pstmt.setString(5, user.getJob());
        pstmt.setString(6, user.getHeight());
        pstmt.setString(7, user.getWeight());

        return (pstmt.executeUpdate() > 0) ? Messenger.SUCCESS : Messenger.FAIL;


    }

}
