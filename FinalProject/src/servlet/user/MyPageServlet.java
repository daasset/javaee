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

@WebServlet(value = "/my-page")
public class MyPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser != null) {
            RequestAlertsSetter.setAlerts(req);
            req.setAttribute("categories", CategoryDAO.findAll());
            req.getRequestDispatcher("/user/my-page.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectStr = null;
        String nowAllowedUrl = "/";
        String successUrl = req.getRequestURI() + "?success=";
        String errorUrl = req.getRequestURI() + "?error=";

        String name = req.getParameter("user-name");
        String surname = req.getParameter("user-surname");
        String password = req.getParameter("user-password");
        String password2 = req.getParameter("user-password2");

        User curUser = (User) req.getSession().getAttribute("currentUser");
        if (curUser != null) {
            if (password.equals(password2)) {
                curUser.setName(name);
                curUser.setSurname(surname);
                curUser.setPassword(password);
                if (UserDAO.edit(curUser)) {
                    redirectStr = successUrl + "you_have_successfully_changed_your_account_details";
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
