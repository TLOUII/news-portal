package com.meiliev.database.repository;

import com.meiliev.database.entity.User;

import java.util.List;

public interface UserRepository {

    public List<User> findAll();

    public User findById(Long id);

    public User findByUsername(String username);

    public void createUser(User user); // переделал в void

    public void updatePasswordUserById(Long id, String pswd); // переделал в void И ИЗМЕНИЛ ПАРАМЕТРЫ АРГУМЕНТА (long id,User user) -> (Long id, String password)
    public void updateUsernameUserById(Long id, String username); //Добавил новы метод для именения имени
}
