package kz.bitlab.javaee.task2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(value = "/task2")
public class Task2Servlet extends HttpServlet {
    private final DBManager db = new DBManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<table cellpadding=\"10\" style=\"border: 1px solid lightgrey; font-family: Arial;\">");
        out.print("<tr>");
        for (String header : db.getHeaders()) {
            out.print("<td style=\"background-color: lightgrey; font-size: 19px; font-weight: bold;\">");
            out.print(header);
            out.print("</td>");
        }
        out.print("</tr>");

        for (User employee: db.getUsers()) {
            out.print("<tr>");
            out.print("<td style=\"font-size: 20px;\">");
            out.print(employee.getName());
            out.print("</td>");

            out.print("<td style=\"font-size: 20px;\">");
            out.print(employee.getSurname());
            out.print("</td>");

            out.print("<td style=\"font-size: 20px;\">");
            out.print(employee.getDepartment());
            out.print("</td>");

            out.print("<td style=\"font-size: 20px;\">");
            out.print(employee.getSalary());
            out.print("</td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }
}
