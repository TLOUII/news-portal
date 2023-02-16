package com.meiliev.database.repository.impl;

import com.meiliev.database.entity.Article;
import com.meiliev.database.repository.ArticleRepository;
import com.meiliev.database.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {

    private final SessionFactory sessionFactory;

    public ArticleRepositoryImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Article> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
            Root<Article> root = criteriaQuery.from(Article.class);
            criteriaQuery.select(root);
            Query query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }

    }

    @Override
    public Article findById(Long id) {
        return null;
    }

    @Override
    public Article findByTitle(String title) {
        return null;
    }

    @Override
    public void createArticle(Article article) {

    }

    @Override
    public void updateArticle(Long id, String content) {

    }

    public static void main(String[] args) {
        ArticleRepositoryImpl articleRepository = new ArticleRepositoryImpl();
        System.out.println(articleRepository.findAll());
    }
}
