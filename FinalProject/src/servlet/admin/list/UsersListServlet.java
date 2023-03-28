package servlet.admin.list;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

@WebServlet(value = "/admin/list/users")
public class UsersListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser != null && curUser.getRole() == User.Role.ADMIN) { // allow only andmins
            req.setAttribute("users", UserDAO.findAll());
            req.getRequestDispatcher("/admin/list/users-list.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }
}
