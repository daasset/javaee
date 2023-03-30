package servlet.user;

import dao.CategoryDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser == null) {
            RequestAlertsSetter.setAlerts(req);
            req.setAttribute("categories", CategoryDAO.findAll());
            req.getRequestDispatcher("/user/registration.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectStr = null;
        String nowAllowedUrl = "/";
        String successUrl = "/login?success=";
        String errorUrl = req.getRequestURI() + "?error=";

        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser == null) {
            String email = req.getParameter("user-email");
            String password = req.getParameter("user-password");
            String password2 = req.getParameter("user-password2");
            String name = req.getParameter("user-name");
            String surname = req.getParameter("user-surname");

            if (password.equals(password2)) {
                if (UserDAO.create(new User(null, email, password, name, surname, User.Role.MEMBER))) {
                    redirectStr = successUrl + "you_have_been_successfully_registered_please_enter_your_email_and_password_to_login";
                } else {
                    redirectStr = errorUrl + "the_input_fields_cannot_be_empty";
                }
            } else {
                redirectStr = errorUrl + "the_passwords_you_have_entered_does_not_match";
            }
        } else {
            redirectStr = nowAllowedUrl;
        }

        resp.sendRedirect(redirectStr);
    }
}
