package com.rod.api.board.model;

import com.rod.api.article.model.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardDto {

    private Long id;
    private String boardName;
    private String boardType;

    private List<Article> articles;

}
