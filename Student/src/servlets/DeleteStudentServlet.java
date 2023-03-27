package servlets;

import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/delete-student")
public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String responseStr = req.getRequestURI() + req.getQueryString();
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("student-id"));
        } catch (NumberFormatException e) {}

        if (StudentDAO.delete(id)) {
            responseStr = "/"; // send home
        }

        resp.sendRedirect(responseStr);
    }
}
