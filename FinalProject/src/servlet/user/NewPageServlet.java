package servlet.user;

import dao.CommentDAO;
import dao.NewsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import model.User;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(value = "/news")
public class NewPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {}
        req.setAttribute("news", NewsDAO.findById(id));
        req.setAttribute("comments", CommentDAO.findAllByNewsId(id));
        req.getRequestDispatcher("/user/news-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String responseStr = req.getRequestURI() + "?" + req.getQueryString();

        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser != null) {
            Long newsId = null;
            try {
                newsId = Long.valueOf(req.getParameter("id"));
            } catch (NumberFormatException e) {}
            String text  = req.getParameter("comment-text");

            if (CommentDAO.create(new Comment(
                    null,
                    curUser,
                    NewsDAO.findById(newsId),
                    LocalDateTime.now(),
                    text
            ))) {
                // do nothing for now
            }
        }

        resp.sendRedirect(responseStr);
    }
}
