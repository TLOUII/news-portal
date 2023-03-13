package com.meiliev.service.mapper;

import com.meiliev.service.model.User;

import java.util.List;

public interface UserMapper {
    List<User> mapToService(List<com.meiliev.database.entity.User> source);

    List<com.meiliev.database.entity.User> mapToDatabase(List<User> source);

    User mapToService(com.meiliev.database.entity.User source);

    com.meiliev.database.entity.User mapToDatabase(User source);
}
