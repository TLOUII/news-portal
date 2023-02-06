package com.meiliev.database.repository.UserDao;

import com.meiliev.database.entity.User;
import com.meiliev.database.repository.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static Long PEOPLE_COUNT = 0L;
    private List<User> people;

    private final UserRepositoryImpl userRepository;

    @Autowired
    public UserDAO(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

//    {
//        people = new ArrayList<>();
//
//        people.add(new User(++PEOPLE_COUNT, "Tom" ,"passwd"));
//        people.add(new User(++PEOPLE_COUNT, "Bob" ,"passwd"));
//        people.add(new User(++PEOPLE_COUNT, "Mike","passwd"));
//        people.add(new User(++PEOPLE_COUNT, "Katy","passwd"));
//    }

    public List<User> index() {
        return userRepository.findAll();
    }

    public User show(Long id) {
        // people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return userRepository.findById(id);
    }

    public void save(User user) {
        User user1 = new User(5L,"02.02","susas");
        userRepository.createUser(user1);
//        user.setId(++PEOPLE_COUNT);
//        people.add(user);
    }

    public void updatePass(Long id, User updatedPerson) {

        userRepository.updatePasswordUserById(id,updatedPerson.getPassword());
        userRepository.updateUsernameUserById(id,updatedPerson.getUsername());
      //  User personToBeUpdated = show(id);
//        personToBeUpdated.setUsername(updatedPerson.getUsername());
//        personToBeUpdated.setPassword(updatedPerson.getPassword());
    }

    public void delete(Long id) {
        people.removeIf(p -> p.getId() == id);
    }
}
