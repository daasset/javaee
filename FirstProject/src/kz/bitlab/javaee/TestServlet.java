package kz.bitlab.javaee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/task0/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<table cellpadding=\"5\">");
        for (int i = 0; i < 10; i++) {
            out.print("<tr>");
            for (int j = 0; j < 5; j++) {
                String bgcolour = "lightblue";
                if ((i + j) % 2 == 0) {
                    bgcolour = "lightgreen";
                }
                out.print("<td style=\"background-color: " + bgcolour +"; \">");
                out.print("Row - " + i + ", Column - " + j);
                out.print("</td>");
            }
            out.print("</tr>");
        }
        out.print("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {super.doPost(req, resp);
    }
}
