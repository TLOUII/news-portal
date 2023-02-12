package com.meiliev.database.Dao;

import com.meiliev.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOControl {

    private final UserDAO userInterface;

    @Autowired
    public UserDAOControl(UserDAO userDAO) {
        this.userInterface = userDAO;
    }

    public List<User> index() {
        return userInterface.findAll();
    }

    public User show(Long id) {
        return userInterface.findById(id);
    }

    public void save(User user) {
        userInterface.createUser(user);
    }

    public void updatePass(Long id, User updatedPerson) {
        userInterface.updatePasswordUserById(id, updatedPerson.getPassword());
        userInterface.updateUsernameUserById(id, updatedPerson.getUsername());
    }

    public void delete(Long id) {
        userInterface.deleteUser(id);
    }
}
