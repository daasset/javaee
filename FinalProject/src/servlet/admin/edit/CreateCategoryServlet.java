package servlet.admin.edit;

import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.User;

import java.io.IOException;

@WebServlet(value = "/admin/create/category")
public class CreateCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser != null && curUser.getRole() == User.Role.ADMIN) { // allow only admins
            req.getRequestDispatcher("/admin/edit/create-category.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectStr = "/";
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser != null && curUser.getRole() == User.Role.ADMIN) { // allow only admins
            String name = req.getParameter("category-name");

            if (CategoryDAO.create(new Category(null, name))) {
                redirectStr = "/admin/list/categories";
            } else {
                redirectStr = req.getRequestURI();
            }
        }

        resp.sendRedirect(redirectStr);
    }
}
