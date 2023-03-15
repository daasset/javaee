package task1n2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/task1n2/add-item")
public class AddItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/task1n2/add-item.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("item-name");
        int price = 0;
        try {
            price = Integer.parseInt(req.getParameter("item-price"));
        } catch (Exception e) {}
        int amount = 0;
        try {
            amount = Integer.parseInt(req.getParameter("item-amount"));
        } catch (Exception e) {}

        DBManager.addItem(new Item(null, name, price, amount));

        req.getRequestDispatcher("/task1n2/add-item.jsp").forward(req, resp);
    }
}
