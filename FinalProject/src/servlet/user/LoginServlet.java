package servlet.user;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser == null) {
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String responseStr = "/login?error";

        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser == null) {
            String email = req.getParameter("user-email");
            String password = req.getParameter("user-password");

            User dbUser = UserDAO.findByEmail(email);
            if (dbUser.getPassword().equals(password)) { // passwords match
                req.getSession().setAttribute("currentUser", dbUser);
                responseStr = "/";
            } else {
                responseStr = "/login?error=1";
            }
        } else {
            responseStr = "/";
        }

        resp.sendRedirect(responseStr);
    }
}
