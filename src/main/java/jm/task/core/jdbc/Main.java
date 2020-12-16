package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        User[] insertUsers = new User[] {
            new User("John", "Doe", (byte) 56),
            new User("Dude", "Dudeson", (byte) 32),
            new User("Test", "Testman", (byte) 25),
            new User("Randy", "Marsh", (byte) 48),
        };

        userServiceImpl.createUsersTable();

        Arrays.stream(insertUsers).forEach(user -> {
            userServiceImpl.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());
        });

        List<User> users = userServiceImpl.getAllUsers();
        System.out.println(users);

        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
