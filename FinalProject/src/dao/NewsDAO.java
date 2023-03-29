package dao;

import model.Category;
import model.News;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
    public static boolean create(News news) {
        if (news.getId() != null
                || news.getUser() == null
                || news.getCategory() == null
                || news.getPostedTime() == null
                || news.getTitle() == null || news.getTitle().isEmpty()
                || news.getContent() == null || news.getTitle().isEmpty()) {
            return  false;
        }

        int row = 0;
        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "insert into news (user_id, category_id, posted_time, title, content) values (?, ?, ?, ?, ?)"
             )) {
            stmt.setLong(1, news.getUser().getId());
            stmt.setLong(2, news.getCategory().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(news.getPostedTime()));
            stmt.setString(4, news.getTitle());
            stmt.setString(5, news.getContent());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }

    public static News findById(Long id) {
        if (id == null) {
            return null;
        }

        News news = null;
        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select * from news where id = ?"
             )) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                news = new News(
                        rs.getLong("id"),
                        UserDAO.findById(rs.getLong("user_id")),
                        CategoryDAO.findById(rs.getLong("category_id")),
                        rs.getTimestamp("posted_time").toLocalDateTime(),
                        rs.getString("title"),
                        rs.getString("content"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return news;
    }

    public static List<News> findAll() {
        List<News> newsList = new ArrayList<>();

        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select news.id as n_id, news.posted_time as n_posted_time, news.title as n_title, news.content as n_content, " +
                             "users.id as u_id, users.email as u_email, users.password as u_password, users.name as u_name, users.surname as u_surname, users.role as u_role, " +
                             "category.id as c_id, category.name as c_name " +
                         "from news, users, category " +
                         "where news.user_id = users.id and news.category_id = category.id " +
                         "order by news.posted_time desc"
             )) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("u_id"),
                        rs.getString("u_email"),
                        rs.getString("u_password"),
                        rs.getString("u_name"),
                        rs.getString("u_surname"),
                        User.Role.valueOf(rs.getString("u_role"))
                );
                Category category = new Category(
                        rs.getLong("c_id"),
                        rs.getString("c_name")
                );
                News news = new News(
                        rs.getLong("n_id"),
                        user,
                        category,
                        rs.getTimestamp("n_posted_time").toLocalDateTime(),
                        rs.getString("n_title"),
                        rs.getString("n_content"));
                newsList.add(news);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newsList;
    }
}
