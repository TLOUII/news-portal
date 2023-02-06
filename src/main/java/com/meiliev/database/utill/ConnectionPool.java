package com.meiliev.database.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final String URL = "jdbc:mysql://localhost:49161/sys";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "cdUSer";
    private static final int MAX_CONNECTIONS = 10;
    private static final int MIN_CONNECTIONS = 5;
    private BlockingQueue<Connection> arrayBlockingQueue = new ArrayBlockingQueue<>(MAX_CONNECTIONS);

    private static ConnectionPool connectionPool;

    public static ConnectionPool getConnectionPool() {
        if (connectionPool == null) {
            return new ConnectionPool();
        }
        return connectionPool;
    }

    private ConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < MIN_CONNECTIONS; i++) {
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

    public synchronized void returnConnection(Connection connection) {
        try {
            arrayBlockingQueue.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSizeOfPool() {
        return arrayBlockingQueue.size();
    }
}
