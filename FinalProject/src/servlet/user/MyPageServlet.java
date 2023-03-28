package servlet.user;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

@WebServlet(value = "/my-page")
public class MyPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User)req.getSession().getAttribute("currentUser");
        if (curUser != null) {
            req.getRequestDispatcher("/user/my-page.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User curUser = (User) req.getSession().getAttribute("currentUser");
        String redirectStr = "/";
        if (curUser != null) {
            curUser.setName(req.getParameter("user-name"));
            curUser.setSurname(req.getParameter("user-surname"));
            UserDAO.edit(curUser);
            redirectStr = req.getRequestURI();
        }

        resp.sendRedirect(redirectStr);
    }
}
