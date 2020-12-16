package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            String queryText = "CREATE TABLE users (id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), lastName VARCHAR(255), age SMALLINT);";
            Statement statement = connection.createStatement();

            statement.execute(queryText);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            String queryText = "DROP TABLE users;";
            Statement statement = connection.createStatement();

            statement.execute(queryText);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            String queryText = "INSERT INTO users (name, lastName, age) values (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(queryText);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            String queryText = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(queryText);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection()) {
            String queryText = "SELECT * FROM users;";
            Statement statement = connection.createStatement();

            ResultSet rows = statement.executeQuery(queryText);

            while (rows.next()) {
                String name = rows.getString(2);
                String lastName = rows.getString(3);
                byte age = rows.getByte(4);

                User user = new User(name, lastName, age);
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            String queryText = "TRUNCATE TABLE users;";
            Statement statement = connection.createStatement();

            statement.execute(queryText);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
