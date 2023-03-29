package servlet.admin.edit;

import dao.CategoryDAO;
import dao.NewsDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.News;
import model.User;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(value = "/admin/create/news")
public class CreateNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser != null && curUser.getRole() == User.Role.ADMIN) { // allow only admins
            req.setAttribute("categories", CategoryDAO.findAll());
            req.getRequestDispatcher("/admin/edit/create-news.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectStr = "/";
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser != null && curUser.getRole() == User.Role.ADMIN) { // allow only admins
            String categoryStr = req.getParameter("news-category");
            String title = req.getParameter("news-title");
            String content = req.getParameter("news-content");

            Category category = CategoryDAO.findByName(categoryStr);

            News news = new News(
                    null,
                    UserDAO.findById(curUser.getId()),
                    category == null? null : CategoryDAO.findById(category.getId()),
                    LocalDateTime.now(),
                    title,
                    content);

            if (NewsDAO.create(news)) {
                redirectStr = "/admin/list/news";
            } else {
                redirectStr = req.getRequestURI();
            }
        }

        resp.sendRedirect(redirectStr);


    }
}
