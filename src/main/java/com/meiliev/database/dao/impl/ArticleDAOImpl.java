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
import java.util.logging.Logger;

@Component
public class ArticleDAOImpl implements ArticleDAO {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final Connection connection;

    public ArticleDAOImpl() {
        this.connection = ConnectionPool.getConnectionPool().getConnection();

    }

    @Override
    public List<Article> findAll() {
        List<Article> articlesList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM news_portal.article");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                articlesList.add(new Article(resultSet.getLong(resultSet.findColumn("id")),
                        resultSet.getString(resultSet.findColumn("title")),
                        resultSet.getString(resultSet.findColumn("content"))));
            }
        } catch (SQLException e) {
            logger.info("Error in method - findAll");
        }
        return articlesList;
    }

    @Override
    public Article findById(Long id) {
        Article article = new Article();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM news_portal.article WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                article.setId(resultSet.getLong(1));
                article.setTitle(resultSet.getString(2));
                article.setContent(resultSet.getString(3));
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.info("Error in method - findById");
        }
        return article;
    }


    @Override
    public void create(Article article) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO news_portal.article (title,content) VALUES (?,?)")) {
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.info("Error in method - create");
        }
    }


    public void update(Long id, Article article) {
        updateContent(id, article.getContent());
        updateTitle(id, article.getTitle());
    }


    private void updateContent(Long id, String content) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE news_portal.article set content = ? where id = ?;")) {
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, content);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.info("Error in method - updateContent");
        }
    }


    private void updateTitle(Long id, String title) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE news_portal.article set title = ? where id = ?;")) {
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, title);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.info("Error in method - updateTitle");
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM news_portal.article WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.info("Error in method - delete");
        }
    }
}
