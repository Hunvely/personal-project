package com.rod.api.article;

import com.rod.api.enums.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleServiceImpl articleService;

    @GetMapping("/api/articles")
    public Map<?, ?> findAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", Messenger.SUCCESS);
        List<Article> list = (List<Article>) articleService.findAll();
        map.put("result", list);

        return map;
    }
}
