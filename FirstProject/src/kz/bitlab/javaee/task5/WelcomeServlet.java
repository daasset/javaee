package kz.bitlab.javaee.task5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.javaee.task4.Order;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/task5")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Html
        out.print("<html><body>");

        // Form
        out.print("<div style='width: 350px; margin: 10px auto;'>");
        out.print("<form action='/task5' method='post'>");

        // Div for fullname
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 120px; padding-bottom: 5px;'>FULL NAME: </span>");
        out.print("<input type='text' name='fullname' style='width: 210px;'>");
        out.print("</div>");

        // Div for age
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 120px; padding-bottom: 5px;'>AGE: </span>");
        out.print("<input type='number' name='age' style='width: 210px;'>");
        out.print("</div>");

        // Div for gender
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 120px; padding-bottom: 5px;'>GENDER: </span>");
        out.print("<input type='radio' id='gender-male' name='gender' value='male'>");
        out.print("<label for='gender-male'>Male</label>&nbsp;&nbsp;&nbsp;");
        out.print("<input type='radio' id='gender-female' name='gender' value='female'>");
        out.print("<label for='gender-female'>Female</label>");
        out.print("</div>");

        // Div button
        out.print("<div style='padding: 10px; text-align: right;'>");
        out.print("<button>SEND</button>");
        out.print("</div>");

        // Form close
        out.print("</form>");
        out.print("</div>");

        // Html end
        out.print("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullname");
        int age = 0;
        try {
             age = Integer.parseInt(req.getParameter("age"));
        } catch (NumberFormatException e) {
        }
        String gender = req.getParameter("gender");
        if (gender == null) {
            gender = "";
        }

        String greetingPhrase = "";
        if (gender.equals("male")) {
            greetingPhrase = "Dude Mister";
        } else if (gender.equals("female")) {
            greetingPhrase = "Dear Miss";
        } else {
            greetingPhrase = "Who Dis";
        }

        PrintWriter out = resp.getWriter();
        // Main div
        out.print("<div style='width: 800px; margin: 10px auto;'>");

        // Greetings
        out.print("<h1>");
        out.print(String.format("Hello %s %s of %d age!",
                greetingPhrase, fullName, age));
        out.print("</h1>");

        // Div close
        out.print("</div>");
    }
}
