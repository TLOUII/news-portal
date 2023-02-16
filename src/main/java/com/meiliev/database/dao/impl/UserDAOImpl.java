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

@Component
public class UserDAOImpl implements UserDAO {

    private final Connection connection;

    public UserDAOImpl() {
        this.connection = ConnectionPool.getConnectionPool().getConnection();
    }

    public List<User> getListUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement prep = connection.prepareStatement("SELECT * FROM news_portal.users"); ResultSet rSet = prep.executeQuery()) {
            while (rSet.next()) {
                long id = rSet.getLong(1);
                String username = rSet.getString(2);
                String password = rSet.getString(3);
                users.add(new User(id, username, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User findUserById(Long id) {
        User user = new User();
        try (ResultSet resultSet = connection.prepareStatement("SELECT * FROM news_portal.users").executeQuery()) {
            while (resultSet.next()) {
                if (resultSet.getLong(1) == id) {
                    user.setId(resultSet.getLong(1));
                    user.setUsername(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                }
            }
        } catch (SQLException e) {
            //ignored
            System.out.println("*");
        }

        return user;
    }


    public void createUser(User user) {
        try (PreparedStatement prep = connection.prepareStatement("INSERT INTO news_portal.users (username,password) VALUES (?,?)")) {
            prep.setString(1, user.getUsername());
            prep.setString(2, user.getPassword());
            prep.execute();
        } catch (SQLException e) {
            System.out.println("*8*");
        }
    }

    @Override
    public void update(Long id, User user) {
        updatePasswordUserById(id, user.getPassword());
        updateUsernameUserById(id, user.getUsername());
    }

    @Override
    public void updatePasswordUserById(Long id, String password) {
        try (PreparedStatement prep = connection.prepareStatement("UPDATE news_portal.users SET password = (?) WHERE id = (?) ")) {
            prep.setLong(2, id);
            prep.setString(1, password);
            prep.execute();
        } catch (SQLException e) {
            //ignored
            System.out.println("*8*");
        }

    }

    @Override
    public void updateUsernameUserById(Long id, String username) {
        try (PreparedStatement prep = connection.prepareStatement("UPDATE news_portal.users SET username = (?) WHERE id = (?) ")) {
            prep.setLong(2, id);
            prep.setString(1, username);
            prep.execute();
        } catch (SQLException e) {
            //ignored
            System.out.println("*8*");
        }
    }

    @Override
    public void deleteUser(Long id) {
        try (PreparedStatement prep = connection.prepareStatement("DELETE FROM news_portal.users WHERE id = (?)")) {
            prep.setLong(1, id);
            prep.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
