package com.meiliev.service.impl;

import com.meiliev.database.repository.UserRepository;
import com.meiliev.service.mapper.UserMapper;
import com.meiliev.service.model.Article;
import com.meiliev.service.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    private static User user;

    @Mock
    private static List<Article> articles;

    @BeforeAll
    static void init() {
        user = new User(1L, "TestUser", "testPSWD");
    }

    @Test
    void getAll() {
        userService.getAll();
        verify(userMapper).mapToService(anyList());
        verify(userRepository).findAll();
    }

    @Test
    void getById() {
        userService.getById(user.getId());
        verify(userRepository).findById(anyLong());
    }

    @Test
    void createUser() {
        userService.createUser(user);
        verify(userRepository).createUser(any());
        verify(userMapper).mapToDatabase(any(User.class));

    }

    @Test
    void changeUser() {
        userService.changeUser(user.getId(), user);
        verify(userRepository).updateUserById(anyLong(), any());
    }

    @Test
    void remove() {
        Long id = 9L;
        userService.remove(id);
        verify(userRepository).delete(id);
    }
}