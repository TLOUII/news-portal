package com.meiliev.rest.controller;

import com.meiliev.service.ArticleService;
import com.meiliev.service.model.Article;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news-portal/articles")
public class ArticleController {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final ArticleService articleService;

    public ArticleController(
            com.meiliev.service.ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAll().orElseThrow(() -> new RuntimeException("there are no one articles yet exist")));
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody Article article) {
        if (article != null) {
            try {
                articleService.createArticle(article);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (RuntimeException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        } else {
            logger.log(Level.SEVERE, "error in method - create ");
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @RequestMapping("{id}")
    public ResponseEntity<?> getArticleById(@PathVariable("id") Long id) {
        if (id > 0 && id instanceof Long) { // тут спорно на счёт инстанс, но ведь такое метсо может быть?
            try {
                return ResponseEntity.ok(articleService.getById(id).orElseThrow(
                        ()
                                -> new Throwable(
                                "not found current article from this id")));
            } catch (Throwable e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(e.getMessage());
            }
        } else {
            logger.log(Level.SEVERE, "error in method - getArticleByID");
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(
            @RequestBody Article article, @PathVariable("id") Long id) {
        if (id > 0 && id instanceof Long) {
            try {
                articleService.changeArticle(id, article);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (Throwable e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(e.getMessage());
            }
        } else {
            logger.log(Level.SEVERE, "error in method - updateArticle ");
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (id > 0 && id instanceof Long) {
            try {
                articleService.remove(id);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (Throwable e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(e.getMessage());
            }
        } else {
            logger.log(Level.SEVERE, "Error in method delete");
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }
}
