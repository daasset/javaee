package kz.bitlab.javaee.task7;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/task7/show")
public class ShowFootballeresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var footballersList = DBManager.getAllFootballersLis();

        PrintWriter out = resp.getWriter();

        // Main div
        out.print("<div style='width: 450px; margin: 10px auto;'>");

        for (Footballer footballer : footballersList) {
            out.print("<h1 style='color: green;'>");
            out.print(footballer.getName() + " " + footballer.getSurname());
            out.print("</h1>");

            out.print("<h3 style='color: blue;'>");
            out.print("Club " + footballer.getClub());
            out.print("</h3>");

            out.print("<h3 style='color: red;'>");
            out.print("Salary " + footballer.getSalary());
            out.print("</h3>");

            out.print("<h3 style='color: red;'>");
            out.print("Transfer price " + footballer.getTransferPrice());
            out.print("</h3>");
        }

        // Main div close
        out.print("</div>");
    }
}
