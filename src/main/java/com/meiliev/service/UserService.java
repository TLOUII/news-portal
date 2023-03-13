package com.meiliev.service;

import com.meiliev.service.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<List<User>> getAll();

    public Optional<User> getById(Long id);

    public void createUser(User user); // Переделал в void

    public void changeUser(Long id, User content); // Переделал в void

    public void remove(Long id);
}
