package kz.bitlab.javaee.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/example/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {}
        Item item = DBManager.getItem(id);
        req.setAttribute("item", item);
        req.getRequestDispatcher("/example/details.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("item-id"));
        } catch (NumberFormatException e) {}
        String name = req.getParameter("item-name");
        int price = 0;
        try {
            price = Integer.parseInt(req.getParameter("item-price"));
        } catch (NumberFormatException e) {}
        int amount = 0;
        try {
            amount = Integer.parseInt(req.getParameter("item-amount"));
        } catch (NumberFormatException e) {}

        Item item = new Item(id, name, price, amount);

        String redirect;
        if (DBManager.setItem(item)) {
            redirect = "/example/home";
        } else {
            redirect = req.getRequestURL().append("?id=").append(id).toString();
        }

        resp.sendRedirect(redirect);
    }
}
