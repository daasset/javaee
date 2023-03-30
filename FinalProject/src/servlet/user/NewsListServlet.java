package servlet.user;

import dao.CategoryDAO;
import dao.NewsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

import java.io.IOException;

@WebServlet(value = "/")
public class NewsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category currentCategory  = null;
        try {
            currentCategory = CategoryDAO.findById(Long.valueOf(req.getParameter("catId")));
        } catch (NumberFormatException e) {}

        RequestAlertsSetter.setAlerts(req);
        req.setAttribute("currentCategory", currentCategory);
        req.setAttribute("categories", CategoryDAO.findAll());
        req.setAttribute("newsList", NewsDAO.findAllByCategory(currentCategory));
        req.getRequestDispatcher("/user/news-list.jsp").forward(req, resp);
    }
}
