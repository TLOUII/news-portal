package com.meiliev.service.mapper.impl;

import com.meiliev.service.mapper.ArticleMapper;
import com.meiliev.service.model.Article;
import com.meiliev.service.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public List<Article> mapToService(List<com.meiliev.database.entity.Article> source) {
        return source.stream().map(this::mapToService).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<com.meiliev.database.entity.Article> mapToDatabase(List<Article> source) {
        List<com.meiliev.database.entity.Article> articles = new ArrayList<>();
        for (Article article : source) {
            articles.add(mapToDatabase(article));
        }
        return articles;
    }

    @Override
    public Article mapToService(com.meiliev.database.entity.Article source) {
        if (source == null) {
            return null;
        }
        User user = null;
        if (source.getUser() != null) {
            user = new User();
            user.setId(source.getUser().getId());
            user.setPassword(source.getUser().getPassword());
            user.setUsername(source.getUser().getUsername());
        }
        return new Article(source.getId(), source.getTitle(), source.getContent(), user);
    }

    @Override
    public com.meiliev.database.entity.Article mapToDatabase(Article source) {
        if (source == null) {
            return null;
        }
        com.meiliev.database.entity.User user = null;
        if (source.getUser() != null) {
            user = new com.meiliev.database.entity.User();
            user.setId(source.getUser().getId());
            user.setUsername(source.getUser().getUsername());
            user.setPassword(source.getUser().getPassword());
        }
        return new com.meiliev.database.entity.Article(source.getId(), source.getTitle(), source.getContent(), user);
    }

}
