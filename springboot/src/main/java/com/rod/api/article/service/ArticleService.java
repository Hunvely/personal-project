package com.rod.api.article.service;


import com.rod.api.article.model.Article;
import com.rod.api.article.model.ArticleDto;
import com.rod.api.common.service.CommandService;
import com.rod.api.common.service.QueryService;

import java.util.List;

public interface ArticleService extends CommandService<ArticleDto>, QueryService<ArticleDto> {

    default Article dtoToEntity(ArticleDto articleDto) {
        return Article.builder()
                .id(articleDto.getId())
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .build();
    }

    default ArticleDto entityToDto(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }

    List<ArticleDto> findAllByBoardId(Long id);
}
