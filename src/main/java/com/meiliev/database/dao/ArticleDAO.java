package com.meiliev.database.dao;

import com.meiliev.database.entity.Article;

import java.util.List;

public interface ArticleDAO {

    public List<Article> getListArticles();

    public Article findArticleById(Long id);

    public void createArticle(Article article);

    public void update(Long id, Article article);

    public void updateArticleContent(Long id, String password);

    public void updateArticleTitle(Long id, String title);

    public void deleteArticle(Long id);
}
