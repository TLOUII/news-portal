package com.meiliev.service;

import com.meiliev.service.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    public Optional<List<Article>> getAll();

    public Optional<Article> getById(Long id);

    public void createArticle(Article article);

    public void changeArticle(Long id, Article article);

    public void remove(Long id);
}
