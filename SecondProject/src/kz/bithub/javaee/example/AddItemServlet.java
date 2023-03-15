package kz.bithub.javaee.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/example/add-item")
public class AddItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/example/additem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("item-name");
        int price = 0;
        try {
            price = Integer.parseInt(req.getParameter("item-price"));
        } catch (NumberFormatException e) {}

        ArrayList<Item> itemsList = DBManager.getAllItems();
        itemsList.add(new Item(name, price));

        for (Item item : itemsList) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }

        resp.sendRedirect("/example/show-items");
    }
}
