package dao;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public static boolean create(Category category) {
        if (category.getId() != null
                || category.getName() == null || category.getName().isEmpty()) {
            return  false;
        }

        if (findByName(category.getName()) != null) { // Cannot allow multiple categories with same name
            return false;
        }

        int row = 0;
        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "insert into category (name) values (?)"
             )) {
            stmt.setString(1, category.getName());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }

    public static Category findById(Long id) {
        if (id == null) {
            return null;
        }
        Category category = null;

        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select * from category where id = ?"
             )) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                category = new Category(
                        rs.getLong("id"),
                        rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return category;
    }

    public static Category findByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        Category category = null;

        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select * from category where name = ?"
             )) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                category = new Category(
                        rs.getLong("id"),
                        rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return category;
    }
    public static List<Category> findAll() {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select * from category order by id"
             )) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getLong("id"),
                        rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }
}
