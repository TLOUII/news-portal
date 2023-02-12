package com.meiliev.database.Dao;

import com.meiliev.database.entity.Article;

import java.util.List;

public interface ArticleDAO {

    public List<Article> returnAll();

    public Article findById(Long id);

    public Article findByTitle(String title);

    public void createArticle(Article article); // Переделал в void

    public void updateArticleContent(Long id, String content); // Переделал в void
    public void updateArticleTitle(Long id, String title); // Переделал в void

    public void deleteArticle (Long id);
}
