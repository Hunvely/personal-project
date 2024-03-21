package com.rod.api.article;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;


}
