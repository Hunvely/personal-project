package com.rod.api.account;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AccountRepository {

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;
}
