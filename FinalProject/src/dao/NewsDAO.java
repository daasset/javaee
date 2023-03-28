package dao;

import model.Category;
import model.News;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
    public static boolean create(News news) {
        if (news.getId() != null
                || news.getUserId() == null
                || news.getCategoryId() == null
                || news.getPostedTime() == null
                || news.getTitle() == null || news.getTitle().isEmpty()
                || news.getContent() == null || news.getTitle().isEmpty()) {
            return  false;
        }

        if (CategoryDAO.findById(news.getCategoryId()) == null) {
            return false;
        }

        if (UserDAO.findById(news.getUserId()) == null) {
            return false;
        }

        int row = 0;
        try (PreparedStatement stmt = Application.INSTANCE.dataSource().getConnection().prepareStatement(
                "insert into news (user_id, category_id, posted_time, title, content) values (?, ?, ?, ?, ?)"
        )) {
            stmt.setLong(1, news.getUserId());
            stmt.setLong(2, news.getCategoryId());
            stmt.setTimestamp(3, Timestamp.valueOf(news.getPostedTime()));
            stmt.setString(4, news.getTitle());
            stmt.setString(5, news.getContent());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }
    public static List<News> findAll() {
        List<News> newsList = new ArrayList<>();

        try (PreparedStatement stmt = Application.INSTANCE.dataSource().getConnection().prepareStatement(
                "select * from news order by id"
        )) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                News news = new News(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getLong("category_id"),
                        rs.getTimestamp("posted_time").toLocalDateTime(),
                        rs.getString("title"),
                        rs.getString("content"));
                newsList.add(news);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newsList;
    }
}
