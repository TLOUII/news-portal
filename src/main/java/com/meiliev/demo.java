package com.meiliev;

import com.meiliev.database.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class demo {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User(22L,"sdadsa","sadas");
        session.save(user);
        session.beginTransaction().commit();

    }
}
