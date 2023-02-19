package com.meiliev.database.dao;

import com.meiliev.database.entity.Article;

import java.util.List;

public interface ArticleDAO {

    public List<Article> findAll();

    public Article findById(Long id);

    public void create(Article article);

    public void update(Long id, Article article);

    public void delete(Long id);
}
