package dao;

import model.Comment;
import model.News;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    public static boolean create(Comment comment) {
        if (comment.getId() != null
                || comment.getUser() == null
                || comment.getNews() == null
                || comment.getPostedTime() == null
                || comment.getText() == null || comment.getText().isEmpty()) {
            return false;
        }

        int row = 0;
        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "insert into comment (user_id, news_id, posted_time, text) values (?, ?, ?, ?)"
             )) {
            stmt.setLong(1, comment.getUser().getId());
            stmt.setLong(2, comment.getNews().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(comment.getPostedTime()));
            stmt.setString(4, comment.getText());
            row = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row > 0;
    }

    public static List<Comment> findAllByNewsId(Long newsId) {
        List<Comment> comments = new ArrayList<>();
        if (newsId == null) {
            return comments;
        }

        try (Connection connection = Application.INSTANCE.dataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select c.id, c.user_id, c.posted_time, c.text, u.email, u.password, u.name, u.surname, u.role " +
                             "from comment as c, users as u where c.user_id = u.id and news_id = ? order by c.posted_time desc"
             )) {
            News news = NewsDAO.findById(newsId);

            stmt.setLong(1, newsId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("user_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        User.Role.valueOf(rs.getString("role")));

                Comment comment = new Comment(
                        rs.getLong("id"),
                        user,
                        news,
                        rs.getTimestamp("posted_time").toLocalDateTime(),
                        rs.getString("text"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }
}
