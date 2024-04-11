package com.rod.api.article;

import com.rod.api.article.model.ArticleDto;
import com.rod.api.article.service.ArticleServiceImpl;
import com.rod.api.common.component.Messenger;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleServiceImpl articleService;

    @PostMapping(path = "/save")
    public ResponseEntity<Messenger> save(@RequestParam ArticleDto articleDto) {
        log.info("Received request to save article: {}", articleDto);

        return ResponseEntity.ok(articleService.save(articleDto));
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<ArticleDto>> findAll() throws SQLException {

        return ResponseEntity.ok(articleService.findAll());
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam Long id) {
        log.info("Received request to delete article with ID: {}", id);

        return ResponseEntity.ok(articleService.deleteById(id));
    }
}
