package servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

@WebServlet(value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectStr = null;
        String nowAllowedUrl = "/";
        String successUrl = "/?success=";

        User currentUser = (User)req.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            req.getSession().setAttribute("currentUser", null);
            redirectStr = successUrl + "you_have_successfully_logged_out";
        } else {
            redirectStr = nowAllowedUrl;
        }
        resp.sendRedirect(redirectStr);
    }
}
