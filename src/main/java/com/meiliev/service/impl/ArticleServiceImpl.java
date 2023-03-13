package com.meiliev.service.impl;

import com.meiliev.database.repository.ArticleRepository;
import com.meiliev.service.mapper.ArticleMapper;
import com.meiliev.service.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements com.meiliev.service.ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public Optional<List<Article>> getAll() {
        return Optional.of(articleMapper.mapToService(articleRepository.findAll()));
    }

    @Override
    public Optional<Article> getById(Long id) {
        return Optional.ofNullable(articleMapper.mapToService(articleRepository.findById(id)));
    }

    @Override
    public void createArticle(Article article) {
        articleRepository.createArticle(articleMapper.mapToDatabase(article));
    }

    @Override
    public void changeArticle(Long id, Article article) {
        articleRepository.updateArticle(id, articleMapper.mapToDatabase(article));
    }

    @Override
    public void remove(Long id) {
        articleRepository.delete(id);
    }

}
