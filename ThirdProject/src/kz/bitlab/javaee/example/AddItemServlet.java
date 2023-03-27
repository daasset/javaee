package kz.bitlab.javaee.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/example/add-item")
public class AddItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("items", DBManager.getAllItems());
        req.getRequestDispatcher("/example/add-item.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = "/add-item?error";

        String name = req.getParameter("item-name");
        int price = 0;
        try {
            price = Integer.parseInt(req.getParameter("item-price"));
        } catch (NumberFormatException e) {}
        int amount = 0;
        try {
            amount = Integer.parseInt(req.getParameter("item-amount"));
        } catch (NumberFormatException e) {}

        Item item = new Item(null, name, price, amount);

        if (DBManager.addItem(item)) {
            redirect = "/example/home";
        }

        resp.sendRedirect(redirect);
    }
}
