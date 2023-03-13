package com.meiliev.database.repository;

import com.meiliev.database.entity.User;

import java.util.List;

public interface UserRepository {

    public List<User> findAll();

    public User findById(Long id);

    public void createUser(User user); // переделал в void

    public void updateUserById(Long id, User user); // переделал в void И ИЗМЕНИЛ ПАРАМЕТРЫ АРГУМЕНТА (long id,User user) -> (Long id, String password)
    public void delete(Long id);
}
