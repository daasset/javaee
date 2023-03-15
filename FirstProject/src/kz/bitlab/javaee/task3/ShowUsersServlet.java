package kz.bitlab.javaee.task3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/task3/show-users")
public class ShowUsersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var usersList = DBManager.getAllUsers();

        usersList.add(new User(req.getParameter("name"), req.getParameter("surname")));

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Main div
        out.print("<div style='width: 300px; margin: 10px auto;'>");

        for (int i = 0; i < usersList.size(); i++) {
            // Div for 1 user
            out.print("<div style='padding: 10px;'>");
            out.print("<span style='display: inline-block; padding-bottom: 5px;'>");
            out.print((i + 1) + ". " + usersList.get(i).getName() + " " + usersList.get(i).getSurname());
            out.print("</span>");
            out.print("</div>");
        }

        // Form close
        out.print("</div>");
    }
}
