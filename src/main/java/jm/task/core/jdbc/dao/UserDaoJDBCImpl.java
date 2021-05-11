package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();
    private Statement statement;

    {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "CREATE TABLE users (" +
                "id INT(64) NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL, " +
                "lastname VARCHAR(45) NOT NULL, " +
                "age INT NOT NULL, PRIMARY KEY (id))";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void dropUsersTable() {
        try {
            statement.execute("DROP TABLE users");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = String.format("INSERT INTO users (name, lastname, age) " +
                "VALUES (\"%s\", \"%s\", %d)", name, lastName, age);

        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void removeUserById(long id) {
        String query = String.format("DELETE FROM users WHERE id = %d", id);

        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.getMessage();
        }

    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                result.add(new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }

    public void cleanUsersTable() {
        try {
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
