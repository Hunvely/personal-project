package com.rod.api.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ArticleDto {

    private Long id;
    private String title;
    private String content;
    private String registerDate;
    private Long writerId;
    private Long boardId;
}
