package kz.bitlab.javaee.task3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/task3")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Form
        out.print("<div style='width: 300px; margin: 10px auto;'>");
        out.print("<form action='/task3/show-users' method='post'>");
        // Div for input 1
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 100px; padding-bottom: 5px;'>NAME: </span>");
        out.print("<input type='text' name='name' style='width: 100%'>");
        out.print("</div>");

        // Div for input 2
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 100px; padding-bottom: 5px;'>SURNAME: </span>");
        out.print("<input type='text' name='surname' style='width: 100%'>");
        out.print("</div>");

        // Div for input 3
        out.print("<div style='padding: 10px; text-align: right;'>");
        out.print("<button>SEND</button>");
        out.print("</div>");

        // Form close
        out.print("</form>");
        out.print("</div>");
    }
}
