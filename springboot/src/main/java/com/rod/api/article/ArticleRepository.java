package com.rod.api.article;

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {


}
