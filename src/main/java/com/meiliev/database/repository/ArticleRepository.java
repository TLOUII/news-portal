package com.meiliev.database.repository;

import com.meiliev.database.entity.Article;

import java.util.List;

public interface ArticleRepository {

    public List<Article> returnAll();

    public Article findById(Long id);

    public Article findByTitle(String title);

    public void createArticle(Article article); // Переделал в void

    public void updateArticle(Long id, String content); // Переделал в void
}
