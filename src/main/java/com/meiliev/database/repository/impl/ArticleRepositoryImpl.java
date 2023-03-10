package com.meiliev.database.repository.impl;

import com.meiliev.database.entity.Article;
import com.meiliev.database.repository.ArticleRepository;
import com.meiliev.database.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final SessionFactory sessionFactory;

    public ArticleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
        Article article = new Article();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (session.get(Article.class, id) != null) {
                return session.get(Article.class, id);
            } else
                logger.log(Level.SEVERE, "no such title for current id");
        }
        return article;
    }

    @Override
    public void createArticle(Article article) {
        try (Session session = sessionFactory.openSession()) {
            if (article == null) {
                logger.log(Level.WARNING,
                        "Article cannot be empty, created cannot be finished successfully");
            } else {
                session.beginTransaction();
                session.save(article);
                session.getTransaction().commit();
            }
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "Source with Article is empty");
            e.printStackTrace();
        }
    }

    @Override
    public void updateArticle(Long id, Article article) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (session.get(Article.class, id) == null) {
                logger.log(Level.WARNING, "no such title for current id");
            } else {
                Article newArticle = session.get(Article.class, id);
                newArticle.setContent(newArticle.getContent());
                newArticle.setTitle(newArticle.getTitle());
                session.getTransaction().commit();
            }
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "Source with Article is empty");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            if (session.get(Article.class, id) == null) {
                logger.log(Level.WARNING,
                        "no such title for current id, maybe it's id is not found");
            } else {
                session.beginTransaction();
                Article article = session.get(Article.class, id);
                session.delete(article);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "id not found, or id has empty");
            e.printStackTrace();
        }
    }
}
