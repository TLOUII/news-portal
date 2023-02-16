package com.meiliev.database.dao.impl;

import com.meiliev.database.dao.ArticleDAO;
import com.meiliev.database.entity.Article;
import com.meiliev.database.utill.ConnectionPool;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDAOImpl implements ArticleDAO {

    private final Connection connection;

    public ArticleDAOImpl() {
        this.connection = ConnectionPool.getConnectionPool().getConnection();

    }

    @Override
    public List<Article> getListArticles() {
        List<Article> articlesList = new ArrayList<>();
        try (PreparedStatement prep = connection.prepareStatement("SELECT * FROM news_portal.article");
             ResultSet rSet = prep.executeQuery()) {
            while (rSet.next()) {
                long id = rSet.getLong(1);
                String title = rSet.getString(2);
                String content = rSet.getString(3);
                articlesList.add(new Article(id, title, content));
            }
        } catch (SQLException e) {
            //ignored
        }
        return articlesList;
    }

    @Override
    public Article findArticleById(Long id) {
        Article article = new Article();
        try (ResultSet resultSet = connection.prepareStatement("SELECT * FROM news_portal.article").executeQuery()) {
            while (resultSet.next()) {
                if (resultSet.getLong(1) == id) {
                    article.setId(resultSet.getLong(1));
                    article.setTitle(resultSet.getString(2));
                    article.setContent(resultSet.getString(3));
                }
            }
        } catch (SQLException e) {
            //ignored
            System.out.println("*");
        }
        return article;
    }


    @Override
    public void createArticle(Article article) { // Переделал в void
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO news_portal.article (title,content) VALUES (?,?)")) {
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.execute();
        } catch (SQLException e) {
            //ignored
            System.out.println("*");
        }
    }

    @Override
    public void update(Long id, Article article) {
        updateArticleContent(id, article.getContent());
        updateArticleTitle(id, article.getTitle());
    }

    @Override
    public void updateArticleContent(Long id, String content) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE news_portal.article set content = (?) where id=(?);")) {
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, content);
            preparedStatement.execute();
        } catch (SQLException e) {
            //ignored
            System.out.println("*");
        }
    }

    @Override
    public void updateArticleTitle(Long id, String title) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE news_portal.article set title = (?) where id=(?);")) {
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, title);
            preparedStatement.execute();
        } catch (SQLException e) {
            //ignored
            System.out.println("*");
        }
    }

    @Override
    public void deleteArticle(Long id) {
        try (PreparedStatement prep = connection.prepareStatement("DELETE FROM news_portal.article WHERE id = (?)")) {
            prep.setLong(1, id);
            prep.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
