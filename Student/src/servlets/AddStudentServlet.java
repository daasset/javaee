package servlets;

import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet(value = "/add-student")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add-student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = req.getRequestURI();

        String name = req.getParameter("student-name");
        String surname = req.getParameter("student-surname");
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(req.getParameter("student-birthdate"));
        } catch (DateTimeParseException | NullPointerException e) {}
        String city = req.getParameter("student-city");

        if (StudentDAO.create(new Student(null, name, surname, localDate, city))) {
            response = "/";
        }

        resp.sendRedirect(response);
    }
}
