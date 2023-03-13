package com.meiliev.service.impl;

import com.meiliev.database.repository.ArticleRepository;
import com.meiliev.service.mapper.ArticleMapper;
import com.meiliev.service.model.Article;
import com.meiliev.service.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @InjectMocks
    private ArticleServiceImpl articleService;
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleMapper articleMapper;

    private static Article article;

    @Mock
    private static List<Article> articles;

    @BeforeAll
    static void init() {
        article = new Article(1L, "Mock", "Junit", new User());
        articles = Collections.singletonList(article);
    }

    @Test
    @DisplayName("Test for getAll() method")
    void getAll() {
        articleService.getAll();
        verify(articleMapper).mapToService(any(List.class));
        verify(articleRepository).findAll();
    }

    @Test
    @DisplayName("Test for getById() method")
    void getById() {
        articleService.getById(article.getId());
//        verify(articleMapper).mapToService(any(com.meiliev.database.entity.Article.class)); //any(Article.class);
        verify(articleRepository).findById(anyLong()); // anyLong()

    }

    @Test
    @DisplayName("Test for createArticle() method")
    void createArticle() {
        articleService.createArticle(article);
        verify(articleRepository).createArticle(any());
        verify(articleMapper).mapToDatabase(any(Article.class));
        // Это тут неточно и не ясно спросить у Тёмы
    }

    @Test
    @DisplayName("Test for changeArticle() method")
    void changeArticle() {
        articleService.changeArticle(article.getId(), article);
        verify(articleRepository).updateArticle(anyLong(), any());
    }


    @Test
    @DisplayName("Test for remove() method")
    void remove() {
        Long id = 9L;
        articleService.remove(id);
        verify(articleRepository).delete(id);
    }
}