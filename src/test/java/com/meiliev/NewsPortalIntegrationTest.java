package com.meiliev;

import com.meiliev.service.model.Article;
import com.meiliev.service.model.User;
import com.meiliev.database.repository.ArticleRepository;
import com.meiliev.database.repository.UserRepository;
import com.meiliev.database.repository.impl.ArticleRepositoryImpl;
import com.meiliev.database.repository.impl.UserRepositoryImpl;
import com.meiliev.service.ArticleService;
import com.meiliev.service.UserService;
import com.meiliev.service.impl.ArticleServiceImpl;
import com.meiliev.service.impl.UserServiceImpl;
import com.meiliev.service.mapper.ArticleMapper;
import com.meiliev.service.mapper.UserMapper;
import com.meiliev.service.mapper.impl.ArticleMapperImpl;
import com.meiliev.service.mapper.impl.UserMapperImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.HashSet;
import java.util.Properties;

@Testcontainers
public class NewsPortalIntegrationTest {

    @Container
    private static final MySQLContainer container = new MySQLContainer<>(DockerImageName.parse("mysql:latest"));

    private static ArticleService articleService;
    private static ArticleRepository articleRepository;
    private static UserService userService;
    private static UserRepository userRepository;
    private static ArticleMapper articleMapper;
    private static UserMapper userMapper;
    private static SessionFactory sessionFactory;
    private static Properties properties;

    private static User user;
    private static com.meiliev.service.model.Article article;


    @BeforeAll
    public static void statUp() {
        container.start();
        properties = new Properties();
        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.put("hibernate.connection.password", container.getPassword());
        properties.put("hibernate.connection.url", container.getJdbcUrl());
        properties.put("hibernate.connection.username", container.getUsername());
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        sessionFactory = new Configuration().addProperties(properties).addAnnotatedClass(com.meiliev.database.entity.Article.class).addAnnotatedClass(com.meiliev.database.entity.User.class).buildSessionFactory();
        articleMapper = new ArticleMapperImpl();
        userMapper = new UserMapperImpl();
        articleRepository = new ArticleRepositoryImpl(sessionFactory);
        userRepository = new UserRepositoryImpl(sessionFactory);
        articleService = new ArticleServiceImpl(articleRepository, articleMapper);
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @BeforeEach
    public void init() {
        user = new com.meiliev.service.model.User(1L, "NameUser", "UserPassword");
        article = new com.meiliev.service.model.Article(1L, "TitleArticle", "ArticleContent");

    }

    @Test
    public void newsPortalIntegrationTest() {
        userService.createUser(user);
        userService.getAll();
        userService.getById(user.getId());
        userService.changeUser(user.getId(), new User());
        articleService.createArticle(article);
        articleService.getAll();
        articleService.getById(article.getId());
        articleService.changeArticle(article.getId(), new Article());
    }

    @AfterAll
    public static void shutDown() {

        container.stop();
    }

}
