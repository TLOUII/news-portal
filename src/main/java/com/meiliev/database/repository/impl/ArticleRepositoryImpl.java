package com.meiliev.database.repository.impl;

import com.meiliev.database.entity.Article;
import com.meiliev.database.repository.ArticleRepository;
import com.meiliev.database.utill.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {


    @Override
    public List<Article> returnAll() {
        List<Article> articlesList = new ArrayList<>();
        try (Statement statement = ConnectionPool.getConnection().createStatement();
             ResultSet rSet = statement.executeQuery("SELECT * FROM news_portal.article")) {
            while (rSet.next()) {
                long id = rSet.getLong(1);
                String title = rSet.getString(2);
                String content = rSet.getString(3);
                //int userId= rSet.getInt(4); не знаю нужно было или нет пускай будет потом подумаю что с эти делать
                articlesList.add(new Article(id, title, content));
            }
        } catch (SQLException e) {
            //ignored
        }
        return articlesList;
    }

    @Override
    public Article findById(Long id) {
        Article article = new Article();
        try (ResultSet resultSet = ConnectionPool.getConnection().createStatement().executeQuery("SELECT * FROM news_portal.article")) {
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
    public Article findByTitle(String title) {
        Article article = new Article();
        try (ResultSet resultSet = ConnectionPool.getConnection().createStatement().executeQuery("SELECT * FROM news_portal.article")) {
            while (resultSet.next()) {
                if (resultSet.getString(2).equals(title)) {
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
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement("INSERT INTO news_portal.article (id,title,content) VALUES (?,?,?)")) {
            preparedStatement.setLong(1, article.getId());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getContent());
            preparedStatement.execute();
        } catch (SQLException e) {
            //ignored
            System.out.println("*");
        }
    }

    @Override
    public void updateArticle(Long id, String content) {
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement("UPDATE news_portal.article set content = (?) where id=(?);")) {
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, content);
            preparedStatement.execute();
        } catch (SQLException e) {
            //ignored
            System.out.println("*");
        }
    }
}
