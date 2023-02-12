package com.meiliev.database.Dao;

import com.meiliev.database.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> findAll();
    public User findById(Long id);
    public User findByUsername(String username);
    public void createUser(User user);
    public void updatePasswordUserById(Long id, String pswd);
    public void updateUsernameUserById(Long id, String username);
    public void deleteUser (Long id);
}
