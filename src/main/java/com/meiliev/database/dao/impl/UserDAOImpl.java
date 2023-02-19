package com.meiliev.database.dao.impl;

import com.meiliev.database.dao.UserDAO;
import com.meiliev.database.entity.User;
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
public class UserDAOImpl implements UserDAO {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final Connection connection;

    public UserDAOImpl() {
        this.connection = ConnectionPool.getConnectionPool().getConnection();
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM news_portal.users");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong(resultSet.findColumn("id")),
                        resultSet.getString(resultSet.findColumn("username")),
                        resultSet.getString(resultSet.findColumn("password"))));
            }
        } catch (SQLException e) {
            logger.info("Error in method - findAll ");
        }
        return users;
    }

    public User findById(Long id) {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM news_portal.users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.info("Error in method - findById");
        }
        return user;
    }


    public void create(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO news_portal.users (username,password) VALUES (?,?)")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.info("Error in method - create");
        }
    }

    @Override
    public void update(Long id, User user) {
        updatePasswordById(id, user.getPassword());
        updateUsernameById(id, user.getUsername());
    }


    private void updatePasswordById(Long id, String password) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE news_portal.users SET password = ? WHERE id = ? ")) {
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.info("Error in method - updatePasswordById ");
        }

    }


    private void updateUsernameById(Long id, String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE news_portal.users SET username = ? WHERE id = ? ")) {
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.info("Error in method - updateUsernameById ");
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM news_portal.users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.info("Error in method - delete ");
        }
    }
}
