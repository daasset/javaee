package sprint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bithub.javaee.example.HomeServlet;

import java.io.IOException;

@WebServlet(value = "/sprint/remove-task")
public class RemoveTaskServlet extends HomeServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("task-id"));
        } catch (NumberFormatException e) {
        }

        DBManager.removeTask(id);

        resp.sendRedirect("/sprint/show-tasks");
    }
}
