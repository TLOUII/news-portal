package com.meiliev.database.repository.impl;

import com.meiliev.database.entity.User;
import com.meiliev.database.repository.UserRepository;
import com.meiliev.database.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            Query query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    @Override
    public User findById(Long id) {
        User user = new User();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (session.get(User.class, id) != null) {
                return session.get(User.class, id);
            } else
                logger.info("no such title for current id");
        }
        return user;
    }

    @Override
    public void createUser(User user) {

        try (Session session = sessionFactory.openSession()) {
            if (user == null) {
                logger.log(Level.WARNING, "User cannot be empty");
            } else {
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
            }
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "User empty");
        }
    }

    @Override
    public void updateUserById(Long id, User user) {
        try (Session session = sessionFactory.openSession()) {
            if (id == 0) {
                logger.log(Level.WARNING, "id == 0, cannot be applied");
            }
            if (session.get(User.class, id) == null) {
                logger.log(Level.WARNING, "id - cannot be empty");
            } else {
                session.beginTransaction();
                User newUser = session.get(User.class, id);
                newUser.setUsername(newUser.getUsername());
                newUser.setPassword(newUser.getPassword());
                session.save(newUser);
                session.getTransaction().commit();
            }
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "id == 0 or null");
        }
    }



    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            if (session.get(User.class, id) == null) {
                logger.log(Level.WARNING,
                        "no such title for current id, maybe it's id is not found");
            } else {
                session.beginTransaction();
                User user = session.get(User.class, id);
                session.delete(user);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "id not found, or id has empty");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        User user = new User(1L, "NameUser", "UserPassword");
        UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
        userRepository.createUser(user);
    }
}
