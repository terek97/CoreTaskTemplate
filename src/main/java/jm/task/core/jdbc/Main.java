package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        try (UserService userService = new UserServiceImpl()) {

            userService.createUsersTable(); //table creation
            for (int i = 0; i < 4; i++) {  //creating 4 users and printing message
                userService.saveUser("Name" + i, "LastName" + i, (byte) (i + 20));
                System.out.println("User с именем – Name" + i + " добавлен в базу данных");
            }
            System.out.println(userService.getAllUsers()); //printing all users
            userService.removeUserById(1); //removing user from table by ID
            userService.dropUsersTable(); //table removing
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
