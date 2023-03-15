package kz.bitlab.javaee.task6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/task6")
public class GradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Html
        out.print("<html><body>");

        // Form
        out.print("<div style='width: 350px; margin: 10px auto;'>");
        out.print("<form action='/task6' method='post'>");

        // Div for fullname
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 120px; padding-bottom: 5px;'>FULL NAME: </span>");
        out.print("<input type='text' name='fullname' style='width: 210px;'>");
        out.print("</div>");

        // Div for grade
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 120px; padding-bottom: 5px;'>EXAM POINTS: </span>");
        out.print("<input type='number' name='grade' style='width: 210px;'>");
        out.print("</div>");

        // Div button
        out.print("<div style='padding: 10px; text-align: right;'>");
        out.print("<button>SUBMIT EXAM</button>");
        out.print("</div>");

        // Form close
        out.print("</form>");
        out.print("</div>");

        // Html end
        out.print("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullname = req.getParameter("fullname");
        int grade = 0;
        try {
            grade = Integer.parseInt(req.getParameter("grade"));
        } catch (NumberFormatException e) {
        }

        String mark;
        if (grade >= 90) {
            mark = "A";
        } else if (grade >= 75) {
            mark = "B";
        } else if (grade >= 60) {
            mark = "C";
        } else if (grade >= 50) {
            mark = "D";
        } else {
            mark = "F";
        }

        PrintWriter out = resp.getWriter();

        // Main div
        out.print("<div style='width: 500px; margin: 10px auto;'>");

        // Greetings
        out.print("<h1>");
        out.print(String.format("%s got %s for exam!",
                fullname, mark));
        out.print("</h1>");

        // Div close
        out.print("</div>");
    }
}
