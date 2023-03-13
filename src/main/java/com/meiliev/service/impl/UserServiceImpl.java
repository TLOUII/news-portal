package com.meiliev.service.impl;

import com.meiliev.database.repository.UserRepository;
import com.meiliev.service.UserService;
import com.meiliev.service.mapper.UserMapper;
import com.meiliev.service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.ofNullable(userMapper.mapToService(userRepository.findAll()));
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(userMapper.mapToService(userRepository.findById(id)));
    }

    @Override
    public void createUser(User user) {
        userRepository.createUser(userMapper.mapToDatabase(user));

    }

    @Override
    public void changeUser(Long id, User user) {
        userRepository.updateUserById(id, userMapper.mapToDatabase(user));

    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }
}
