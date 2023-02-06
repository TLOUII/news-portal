package com.meiliev.database.utill;

import java.sql.Connection;
import java.sql.SQLException;

import static com.meiliev.database.utill.ConnectionPool.*;

public class ConnectionPoolDemo {
    public static void main(String[] args) {
        ConnectionPool connectionPool = getConnectionPool();
        System.out.println(connectionPool.getSizeOfPool());
        Connection connection = connectionPool.getConnection();
        try {
            System.out.println(connection.getMetaData().getDriverName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(connectionPool.getSizeOfPool());
        connectionPool.returnConnection(connection);
        System.out.println(connectionPool.getSizeOfPool());
    }
}
