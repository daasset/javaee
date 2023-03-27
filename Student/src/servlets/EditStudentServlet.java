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

@WebServlet(value = "/edit-student")
public class EditStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {}
        req.setAttribute("student", StudentDAO.getStudent(id));
        req.getRequestDispatcher("/edit-student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String responseStr = req.getRequestURI() + req.getQueryString();
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("student-id"));
        } catch (NumberFormatException e) {}
        String name = req.getParameter("student-name");
        String surname = req.getParameter("student-surname");
        LocalDate birtDate = null;
        try {
            birtDate = LocalDate.parse(req.getParameter("student-birthdate"));
        } catch (DateTimeParseException | NullPointerException e) {}
        String city = req.getParameter("student-city");

        Student student = StudentDAO.getStudent(id);
        if (student != null
                && name != null && !name.isEmpty()
                && surname != null && !surname.isEmpty()
                && birtDate != null
                && city != null && !city.isEmpty()) {

            // Allow only if all params are set
            student.setName(name);
            student.setSurname(surname);
            student.setBirthDate(birtDate);
            student.setCity(city);
            StudentDAO.edit(student);

            responseStr = "/student-details?id=" + student.getId();
        }
        resp.sendRedirect(responseStr);
    }
}
