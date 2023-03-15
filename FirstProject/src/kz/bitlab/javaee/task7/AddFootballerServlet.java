package kz.bitlab.javaee.task7;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/task7/add")
public class AddFootballerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Form
        out.print("<div style='width: 450px; margin: 10px auto;'>");
        out.print("<form action='/task7/add' method='post'>");

        // Div for name
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 150px; padding-bottom: 5px;'>NAME: </span>");
        out.print("<input type='text' name='name' style='width: 280px;'>");
        out.print("</div>");

        // Div for surname
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 150px; padding-bottom: 5px;'>SURNAME: </span>");
        out.print("<input type='text' name='surname' style='width: 280px;'>");
        out.print("</div>");

        // Div for club
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 150px; padding-bottom: 5px;'>CLUB: </span>");
        out.print("<select name='club' style='width: 280px; height: 22px;'>");
        out.print("<option>Real Madrid FC</option>");
        out.print("<option>Barcelona FC</option>");
        out.print("<option>Arsenal FC</option>");
        out.print("</select>");
        out.print("</div>");

        // Div for salary
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 150px; padding-bottom: 5px;'>SALARY: </span>");
        out.print("<input type='number' name='salary' style='width: 280px;'>");
        out.print("</div>");

        // Div for transfer fee
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 150px; padding-bottom: 5px;'>TRANSFER FEE: </span>");
        out.print("<input type='number' name='transfer-fee' style='width: 280px;'>");
        out.print("</div>");

        // Div button
        out.print("<div style='padding: 10px; text-align: right;'>");
        out.print("<button style='border: none; background-color: blue; color: white; padding: 7px;'>ADD FOOTBALLER</button>");
        out.print("</div>");

        // Form close
        out.print("</form>");
        out.print("</div>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var footballersList = DBManager.getAllFootballersLis();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String club = req.getParameter("club");
        int salary = 0;
        try {
            salary  = Integer.parseInt(req.getParameter("salary"));
        } catch (NumberFormatException e) {}
        int transferFee = 0;
        try {
            transferFee  = Integer.parseInt(req.getParameter("transfer-fee"));
        } catch (NumberFormatException e) {}

        footballersList.add(new Footballer(
                null, name, surname, club, salary, transferFee
        ));


        PrintWriter out = resp.getWriter();

        // Form
        out.print("<div style='width: 450px; margin: 10px auto;'>");
        out.print("<form action='/task7/add' method='post'>");

        // Div for name
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 150px; padding-bottom: 5px;'>NAME: </span>");
        out.print("<input type='text' name='name' style='width: 280px;'>");
        out.print("</div>");

        // Div for surname
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 150px; padding-bottom: 5px;'>SURNAME: </span>");
        out.print("<input type='text' name='surname' style='width: 280px;'>");
        out.print("</div>");

        // Div for club
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 150px; padding-bottom: 5px;'>CLUB: </span>");
        out.print("<select name='club' style='width: 280px; height: 22px;'>");
        out.print("<option>Real Madrid FC</option>");
        out.print("<option>Barcelona FC</option>");
        out.print("<option>Arsenal FC</option>");
        out.print("</select>");
        out.print("</div>");

        // Div for salary
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 150px; padding-bottom: 5px;'>SALARY: </span>");
        out.print("<input type='number' name='salary' style='width: 280px;'>");
        out.print("</div>");

        // Div for transfer fee
        out.print("<div style='padding: 10px;'>");
        out.print("<span style='display: inline-block; width: 150px; padding-bottom: 5px;'>TRANSFER FEE: </span>");
        out.print("<input type='number' name='transfer-fee' style='width: 280px;'>");
        out.print("</div>");

        // Div button
        out.print("<div style='padding: 10px; text-align: right;'>");
        out.print("<button style='border: none; background-color: blue; color: white; padding: 7px;'>ADD FOOTBALLER</button>");
        out.print("</div>");

        // Form close
        out.print("</form>");
        out.print("</div>");
    }
}
