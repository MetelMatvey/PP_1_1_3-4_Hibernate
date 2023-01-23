package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();
    public UserDaoJDBCImpl() {
    }

   public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String sqlQ = "CREATE TABLE IF NOT EXISTS users.users (\n" +
                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(100) NOT NULL,\n" +
                    "  `lastName` VARCHAR(100) NOT NULL,\n" +
                    "  `age` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`));";
            statement.executeUpdate(sqlQ);
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении таблицы.");
        }
 }
//
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
                String sqlQ = "DROP TABLE IF EXISTS users;";
                statement.executeUpdate(sqlQ);
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы.");
        }
    }
//
    public void saveUser(String name, String lastName, byte age) {
        String sqlQ = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedstatement = connection.prepareStatement(sqlQ)) {
                preparedstatement.setString(1, name);
                preparedstatement.setString(2, lastName);
                preparedstatement.setLong(3, age);
                preparedstatement.executeUpdate();
        } catch (SQLException e) {
                System.out.println("Ошибка при добавлении пользователя.");
            }
    }
//
    public void removeUserById(long id) {
        String sqlQ = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQ)) {
                preparedStatement.setByte(1, (byte) id);
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя.");
        }
    }
//
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlQ = "SELECT * FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQ);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении пользователей.");
        }
        return null;
    }
//
    public void cleanUsersTable() {
        String sqlQ = "TRUNCATE TABLE users.users;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlQ);
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы.");
        }
   }
}
