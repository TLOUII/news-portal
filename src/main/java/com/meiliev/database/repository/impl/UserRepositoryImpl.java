package com.meiliev.database.repository.impl;

import com.meiliev.database.entity.User;
import com.meiliev.database.repository.UserRepository;
import com.meiliev.database.utill.ConnectionPool;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {


    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = ConnectionPool.getConnection().createStatement();
             ResultSet rSet = statement.executeQuery("SELECT * FROM news_portal.users")) {
            while (rSet.next()) {
                long id = rSet.getLong(1);
                String username = rSet.getString(2);
                String password = rSet.getString(3);
                users.add(new User(id, username, password));
            }
        } catch (SQLException e) {
            //ignored
        }
        return users;
    }

    public User findById(Long id) {
        User user = new User();
        try (ResultSet resultSet = ConnectionPool.getConnection().createStatement().executeQuery("SELECT * FROM news_portal.users")) {
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

    public User findByUsername(String username) {
        User user = new User();
        try (ResultSet resultSet = ConnectionPool.getConnection().createStatement().executeQuery("SELECT * FROM news_portal.users")) {
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(username)) {
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
        try (PreparedStatement prep = ConnectionPool.getConnection().prepareStatement("INSERT INTO news_portal.users (username,password) VALUES (?,?)")) {
            prep.setString(1, user.getUsername());
            prep.setString(2, user.getPassword());
            prep.execute();
        } catch (SQLException e) {
            System.out.println("*8*");
        }
    }

    public void updatePasswordUserById(Long id, String password) {
        try (PreparedStatement prep = ConnectionPool.getConnection().prepareStatement("UPDATE news_portal.users SET password = (?) WHERE id = (?) ")) {
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
        try (PreparedStatement prep = ConnectionPool.getConnection().prepareStatement("UPDATE news_portal.users SET username = (?) WHERE id = (?) ")) {
            prep.setLong(2, id);
            prep.setString(1, username);
            prep.execute();
        } catch (SQLException e) {
            //ignored
            System.out.println("*8*");
        }
    }
}
