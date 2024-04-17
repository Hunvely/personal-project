package com.rod.api.article.service;

import com.rod.api.article.model.Article;
import com.rod.api.article.model.ArticleDto;
import com.rod.api.article.repository.ArticleRepository;
import com.rod.api.common.component.Messenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Messenger save(ArticleDto articleDto) {
        entityToDto(articleRepository.save(dtoToEntity(articleDto)));

        return new Messenger();
    }

    @Override
    public Messenger deleteById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if (optionalArticle.isPresent()) {
            articleRepository.deleteById(id);

            return new Messenger();
        } else {
            log.warn("Article with ID {} was not found or deletion was unsuccessful.", id);

            return new Messenger();
        }
    }

    @Override
    public Messenger modify(ArticleDto articleDto) {
        Optional<Article> optionalArticle = articleRepository.findById(articleDto.getId());
        if (optionalArticle.isPresent()) {
            ArticleDto updateArticle = articleDto.toBuilder()
                    .title(articleDto.getTitle())
                    .content(articleDto.getContent())
                    .build();

            articleRepository.save(dtoToEntity(updateArticle));

            return Messenger.builder()
                    .message("update SUCCESS")
                    .build();
        } else {
            log.warn("Article with ID '{}' not found.", articleDto.getId());

            return Messenger.builder()
                    .message("update FAILURE")
                    .build();
        }
    }

    @Override
    public List<ArticleDto> findAll() throws SQLException {
        return articleRepository.findAll().stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    public Optional<ArticleDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return articleRepository.count();
    }

    @Override
    public Boolean existById(Long id) {
        return null;
    }

    @Override
    public List<ArticleDto> findAllByBoardId(Long id) {
        List<Article> articles = articleRepository.findAllByBoardId(id);
        if (articles != null) {
            return articles.stream().map(i -> entityToDto(i)).toList();
        } else {
            log.warn("No board found with the id: {}", id);

            return Collections.emptyList();
        }
    }
}
