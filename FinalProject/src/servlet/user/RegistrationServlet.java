package servlet.user;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser == null) {
            req.getRequestDispatcher("/user/registration.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String responseStr = req.getRequestURI();

        String email = req.getParameter("user-email");
        String password = req.getParameter("user-password");
        String password2 = req.getParameter("user-password2");
        String name = req.getParameter("user-name");
        String surname = req.getParameter("user-surname");

        if (password.equals(password2)) {
            if (UserDAO.create(new User(null, email, password, name, surname, User.Role.MEMBER))) {
                responseStr = "/admin/users-list"; // THIS IS TEMPORARY
            }
        }

        resp.sendRedirect(responseStr);
    }
}
