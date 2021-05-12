package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_NAME = "root";
    private static final String DB_PASS = "1119002";

    /*
    * не знаю точно как лучше реализовать закрытие connection. не понравился вариант, в котором каждый метод в dao
    * получает новое соединение и закрывает его, поэтому решил оставить одно соединение на все приложение, которое
    * создается в этом классе. для закрытия реализовал статик метод closeConnection.
    *
    * после этого подумал, что будет не логичным, что после использования БД в методе main нужно вызывать этот метод,
    * т.к. main работает только с UserServiceImpl, поэтому сделал сервис AutoCloseable и использую его в try с ресурсами.
    */
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
