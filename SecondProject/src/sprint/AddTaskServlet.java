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

@WebServlet(value = "/sprint/add-task")
public class AddTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/sprint/add-task.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("task-name");
        String description = req.getParameter("task-description");
        Date dueDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            dueDate = format.parse(req.getParameter("task-duedate"));
        } catch (ParseException e) {}

        // Temp solution for not entering correctly
        if (name == null || name.isEmpty()
                || description == null
                || dueDate == null) {
            resp.sendRedirect("/sprint/add-task");
            return;
        }

        Task newTask = new Task(null, name, description, dueDate, false);
        DBManager.addTask(newTask);

        resp.sendRedirect("/sprint/show-tasks");
    }
}
