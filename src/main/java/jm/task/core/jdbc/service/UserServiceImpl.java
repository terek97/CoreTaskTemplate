package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDAO = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDAO.createUsersTable();
    }

    public void dropUsersTable() {
        userDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDAO.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void cleanUsersTable() {
        userDAO.cleanUsersTable();
    }

    @Override
    public void close() throws Exception {
        Util.closeConnection();
    }
}
