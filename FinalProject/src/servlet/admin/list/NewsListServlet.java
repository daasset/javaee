package servlet.admin.list;

import dao.NewsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

@WebServlet(value = "/admin/list/news")
public class NewsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser != null && curUser.getRole() == User.Role.ADMIN) { // allow only andmins
            req.setAttribute("newsList", NewsDAO.findAll());
            req.getRequestDispatcher("/admin/list/news-list.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }
}
