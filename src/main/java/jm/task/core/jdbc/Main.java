package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Александр","Розенбаум", (byte) 50);
        userService.saveUser("Григорий","Лепс", (byte) 50);
        userService.saveUser("Владимир","Высоцкий", (byte) 50);
        userService.saveUser("Муслим","Магомаев", (byte) 50);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
