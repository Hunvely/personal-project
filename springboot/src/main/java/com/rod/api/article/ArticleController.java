package com.rod.api.article;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@RestController
public class ArticleController {

    private ArticleServiceImpl articleService;


}
