package kz.bitlab.javaee.task4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/task4/order")
public class OrderSevlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Form
        out.print("<div style='width: 400px; margin: 10px auto;'>");
        out.print("<form action='/task4/order' method='post'>");

        // Div for name
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 100px; padding-bottom: 5px;'>NAME: </span>");
        out.print("<input type='text' name='name' style='width: 280px;'>");
        out.print("</div>");

        // Div for surname
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 100px; padding-bottom: 5px;'>SURNAME: </span>");
        out.print("<input type='text' name='surname' style='width: 280px;'>");
        out.print("</div>");

        // Div for food
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 100px; padding-bottom: 5px;'>FOOD: </span>");
        out.print("<select name='food' style='width: 280px; height: 22px;'>");
        out.print("<option>Manty - 1500 KZT</option>");
        out.print("<option>Plov - 1600 KZT</option>");
        out.print("<option>Beshbarmak - 2000 KZT</option>");
        out.print("</select>");
        out.print("</div>");

        // Div button
        out.print("<div style='padding: 10px; text-align: right;'>");
        out.print("<button>ORDER</button>");
        out.print("</div>");

        // Form close
        out.print("</form>");
        out.print("</div>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Order> ordersList = DBManager.getAllOrders();
        ordersList.add(new Order(req.getParameter("name"), req.getParameter("surname"), req.getParameter("food")));

        PrintWriter out = resp.getWriter();

        // Main div
        out.print("<div style='width: 800px; margin: 10px auto;'>");

        // Div for last order
        Order lastOrder = ordersList.get(ordersList.size() - 1);

        out.print("<div style='padding: 10px;'>");
        out.print("<h1>");
        out.print(String.format("%s %s ordered %s", lastOrder.getName(), lastOrder.getSurname(), lastOrder.getOrderName()));
        out.print("</h1>");
        out.print("</div>");

        // Form close
        out.print("</div>");

    }
}
