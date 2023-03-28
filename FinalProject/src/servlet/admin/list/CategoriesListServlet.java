package servlet.admin.list;

import dao.CategoryDAO;
import dao.NewsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

@WebServlet(value = "/admin/list/categories")
public class CategoriesListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser != null && curUser.getRole() == User.Role.ADMIN) { // allow only andmins
            req.setAttribute("categories", CategoryDAO.findAll());
            req.getRequestDispatcher("/admin/list/categories-list.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }
}
