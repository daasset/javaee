package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static boolean create(User user) {
        if (user.getId() != null
            || user.getEmail() == null || user.getEmail().isEmpty()
            || user.getPassword() == null || user.getPassword().isEmpty()
            || user.getName() == null || user.getName().isEmpty()
            || user.getSurname() == null || user.getSurname().isEmpty()
            || user.getRole() == null) {
            return  false;
        }

        if (findByEmail(user.getEmail()) != null) { // Cannot allow multiple users with same email
            return false;
        }

        int row = 0;
        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "insert into users (email, password, name, surname, role) values (?, ?, ?, ?, ?)"
             )) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getSurname());
            stmt.setString(5, user.getRole().name());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }

    public static boolean edit(User user) {
        // Checking if all fields are set
        if (user.getId() == null
                || user.getEmail() == null || user.getEmail().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()
                || user.getName() == null || user.getName().isEmpty()
                || user.getSurname() == null || user.getSurname().isEmpty()
                || user.getRole() == null) {
            return  false;
        }

        int row = 0;
        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "update users set name = ?, surname = ? where id = ?"
             )) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setLong(3, user.getId());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }

    public static User findById(Long id) {
        if (id == null) {
            return null;
        }
        User user = null;
        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select * from users where id = ?"
             )) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        User.Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public static User findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        User user = null;
        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select * from users where email = ?"
             )) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        User.Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public static List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select * from users order by id"
             )) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        User.Role.valueOf(rs.getString("role")));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
}
