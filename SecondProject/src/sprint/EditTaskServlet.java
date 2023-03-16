package sprint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/sprint/edit-task")
public class EditTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        req.setAttribute("task", DBManager.findTaskById(id));
        req.getRequestDispatcher("/sprint/edit-task.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("task-id"));
        } catch (NumberFormatException e) {
        }
        String name = req.getParameter("task-name");
        String description = req.getParameter("task-description");
        Date dueDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            dueDate = format.parse(req.getParameter("task-duedate"));
        } catch (ParseException e) {}
        boolean isDone = Boolean.valueOf(req.getParameter("task-isdone"));

        // Temp solution for not entering correctly
        if (name == null || name.isEmpty()
                || description == null
                || dueDate == null) {
            resp.sendRedirect(req.getRequestURL().append("?id=").append(id).toString());
            return;
        }

        Task editingTask = new Task(id, name, description, dueDate, isDone);
        DBManager.editTask(editingTask);

        resp.sendRedirect("/sprint/show-tasks");
    }
}
