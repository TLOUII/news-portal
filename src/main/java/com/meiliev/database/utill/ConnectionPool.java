package com.meiliev.database.utill;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool {
    private final String URL = "jdbc:mysql://localhost:49161/news_portal";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";
    private final int MAX_CONNECTIONS = 10;
    private final BlockingQueue<Connection> arrayBlockingQueue = new ArrayBlockingQueue<>(MAX_CONNECTIONS);


    private static ConnectionPool connectionPool;

    public static ConnectionPool getConnectionPool() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    private ConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            try {
                arrayBlockingQueue.put(DriverManager.getConnection(URL, LOGIN, PASSWORD));
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Connection getConnection() {
        try {
            return arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
