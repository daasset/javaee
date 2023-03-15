package kz.bitlab.javaee.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/items")
public class ItemsServlet extends kz.bitlab.javaee.HomeServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Item> items = DBManager.getAllItems();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        for (Item item : items) {
            out.print("<h1>");
            out.print(item.getName() + " - " + item.getPrice());
            out.print("</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
