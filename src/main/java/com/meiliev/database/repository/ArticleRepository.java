package com.meiliev.database.repository;

import com.meiliev.database.entity.Article;

import java.util.List;

public interface ArticleRepository {

    public List<Article> findAll();

    public Article findById(Long id);

    public void createArticle(Article article); // Переделал в void

    public void updateArticle(Long id, Article article); // Переделал в void

    public void delete(Long id);
}
