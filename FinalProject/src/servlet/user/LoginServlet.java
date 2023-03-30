package servlet.user;

import dao.CategoryDAO;
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
            RequestAlertsSetter.setAlerts(req);
            req.setAttribute("categories", CategoryDAO.findAll());
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectStr = null;
        String nowAllowedUrl = "/";
        String successUrl = "/?success=";
        String errorUrl = req.getRequestURI() + "?error=";

        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser == null) {
            String email = req.getParameter("user-email");
            String password = req.getParameter("user-password");

            User dbUser = UserDAO.findByEmail(email);
            if (dbUser == null || dbUser.getPassword().equals(password)) { // passwords match
                req.getSession().setAttribute("currentUser", dbUser);
                redirectStr = successUrl + "you_have_successfully_logged_in";
            } else {
                redirectStr = errorUrl + "invalid_email_and_password_entered";
            }
        } else {
            redirectStr = nowAllowedUrl;
        }

        resp.sendRedirect(redirectStr);
    }
}
