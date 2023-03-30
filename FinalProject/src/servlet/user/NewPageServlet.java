package servlet.user;

import dao.CategoryDAO;
import dao.CommentDAO;
import dao.NewsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import model.News;
import model.User;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(value = "/news")
public class NewPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        News news = null;
        try {
            news = NewsDAO.findById(Long.valueOf(req.getParameter("id")));
        } catch (NumberFormatException e) {}

        if (news != null) {
            RequestAlertsSetter.setAlerts(req);
            req.setAttribute("categories", CategoryDAO.findAll());
            req.setAttribute("news", news);
            req.setAttribute("comments", CommentDAO.findAllByNews(news));
            req.getRequestDispatcher("/user/news-page.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectStr = null;
        String nowAllowedUrl = req.getRequestURI() + "?" + req.getQueryString();
        String successUrl = req.getRequestURI() + "?" + req.getQueryString() + "&success=";
        String errorUrl = req.getRequestURI() + "?" + req.getQueryString() + "&error=";

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
                redirectStr = successUrl + "your_comment_have_successfully_been_posted";
            } else {
                if (text.isEmpty()) {
                    redirectStr = errorUrl + "comment_cannot_be_an_empty_text";
                } else {
                    redirectStr = errorUrl + "your_comment_is_too_long";
                }
            }
        } else {
            redirectStr = nowAllowedUrl;
        }

        resp.sendRedirect(redirectStr);
    }
}
