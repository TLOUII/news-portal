package com.meiliev.database.dao;

import com.meiliev.database.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> findAll();
    public User findById(Long id);
    public void create(User user);
    public void update (Long id,User user);
    public void delete(Long id);
}
