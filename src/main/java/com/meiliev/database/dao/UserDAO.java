package com.meiliev.database.dao;

import com.meiliev.database.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> getListUsers();
    public User findUserById(Long id);
    public void createUser(User user);
    public void update (Long id,User user);
    public void updatePasswordUserById(Long id, String password);
    public void updateUsernameUserById(Long id, String username);
    public void deleteUser (Long id);
}
