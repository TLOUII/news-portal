package com.meiliev.service.mapper;

import com.meiliev.service.model.Article;

import java.util.List;

public interface ArticleMapper {

    List<Article> mapToService(List<com.meiliev.database.entity.Article> source);

    List<com.meiliev.database.entity.Article> mapToDatabase(List<Article> source);

    Article mapToService(com.meiliev.database.entity.Article source);

    com.meiliev.database.entity.Article mapToDatabase (Article source);
}
