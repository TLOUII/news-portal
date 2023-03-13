package com.meiliev.service.mapper.impl;


import com.meiliev.service.mapper.UserMapper;
import com.meiliev.service.model.Article;
import com.meiliev.service.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<User> mapToService(List<com.meiliev.database.entity.User> source) {
        return source.stream().map(this::mapToService).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<com.meiliev.database.entity.User> mapToDatabase(List<User> source) {
        List<com.meiliev.database.entity.User> users = new ArrayList<>();
        for (User user : source) {
            users.add(mapToDatabase(user));
        }
        return users;
    }

    @Override
    public User mapToService(com.meiliev.database.entity.User source) {
        if (source == null) {
            return null;
        } else if (source.getArticles() == null) {
            return new User(source.getId(), source.getUsername(), source.getPassword());
        }
        return new User(source.getId(), source.getUsername(), source.getPassword(), convertToDB(source));
    }

    @Override
    public com.meiliev.database.entity.User mapToDatabase(User source) {
        if (source == null) {
            return null;
        } else if (source.getArticles() == null && source != null) {
            return new com.meiliev.database.entity.User(source.getId(), source.getUsername(), source.getPassword());
        } else
            return new com.meiliev.database.entity.User(source.getId(), source.getUsername(), source.getPassword(), convertToService(source));
    }

    private Set<Article> convertToDB(com.meiliev.database.entity.User source) {
        User user = new User(source.getId(), source.getUsername(), source.getPassword());
        Set<Article> articles = new HashSet<>();
        for (com.meiliev.database.entity.Article article : source.getArticles()) {
            articles.add(new Article(article.getId(), article.getTitle(), article.getContent(), user));
        }
        return articles;
    }

    private Set<com.meiliev.database.entity.Article> convertToService(User source) {
        com.meiliev.database.entity.User user = new com.meiliev.database.entity.User(source.getId(), source.getUsername(), source.getPassword());
        Set<com.meiliev.database.entity.Article> articles = new HashSet<>();
        for (Article article : source.getArticles()) {
            articles.add(new com.meiliev.database.entity.Article(article.getId(), article.getTitle(), article.getContent(), user));
        }
        return articles;
    }

}
