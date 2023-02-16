package com.meiliev.service.mapper.impl;

import com.meiliev.service.mapper.ArticleMapper;
import com.meiliev.service.model.Article;

import java.util.List;

public class ArticleMapperImpl implements ArticleMapper {
    @Override
    public List<Article> mapToService(List<com.meiliev.database.entity.Article> source) {
        return null;
    }

    @Override
    public List<com.meiliev.database.entity.Article> mapToDatabase(List<Article> source) {
        return null;
    }

    @Override
    public Article mapToService(com.meiliev.database.entity.Article source) {
        return null;
    }

    @Override
    public com.meiliev.database.entity.Article mapToDatabase(Article source) {
        return null;
    }
}
