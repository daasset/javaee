package kz.bitlab.javaee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(value = "/task0/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        ArrayList<String> items = new ArrayList<>(Arrays.asList(
                "Iphone14",
                "Iphone13",
                "Iphone12",
                "Iphone11",
                "Iphone10"));

        out.print("<table>");
        for (int i = 0; i < items.size(); i++) {
            out.print("<tr>");
            out.print("<td>");
            out.print("<h1>");
            out.print(i + 1 + ".");
            out.print("</h1>");
            out.print("</td>");
            out.print("<td>");
            out.print("<h1>");
            out.print(items.get(i));
            out.print("</h1>");
            out.print("</td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
