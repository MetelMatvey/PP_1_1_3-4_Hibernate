package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

     UserDao userDaoHibernate = new UserDaoHibernateImpl();
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();

    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("Пользователь с именем "+ name + " добавлен в базу данных.");
    }

    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
        System.out.println("Пользователь с id = " + id + " был успешно удален.");

    }

    public List<User> getAllUsers() {
        List<User> userList = userDaoHibernate.getAllUsers();
        System.out.println(userList);
        return userList;
    }

    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
