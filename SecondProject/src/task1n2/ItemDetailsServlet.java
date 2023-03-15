package task1n2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/task1n2/item-details")
public class ItemDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {}


        if (id != null) {
            Item item = DBManager.getItemById(id);
            if (item != null) {
                req.setAttribute("item", item);
            }
        }

        req.getRequestDispatcher("/task1n2/item-details.jsp").forward(req, resp);
    }
}
