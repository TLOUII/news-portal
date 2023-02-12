package com.meiliev.database.Dao;

import com.meiliev.database.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDAOControl {

    private final ArticleDAO articleDAO;

    @Autowired
    public ArticleDAOControl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public List<Article> showAll() {
        return articleDAO.returnAll();
    }

    public Article showOne(Long id) {
        return articleDAO.findById(id);
    }

    public void saveNewArticle(Article article) {
        articleDAO.createArticle(article);
    }

    public Article show(Long id) {
        return articleDAO.findById(id);
    }

    public void updateArticle(Long id, Article changedArticle) {
        articleDAO.updateArticleTitle(id, changedArticle.getTitle());
        articleDAO.updateArticleContent(id, changedArticle.getContent());
    }

    public void deleteArticle(Long id) {
        articleDAO.deleteArticle(id);
    }
}
