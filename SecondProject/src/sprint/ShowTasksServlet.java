package sprint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

@WebServlet(value = "/sprint/show-tasks")
public class ShowTasksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Random rand = new Random();
//        for (int i = 0; i < 5; i++) {
//            Task temp = new Task(null, "TestTask " + rand.nextInt(10), "for testing", new Date(), rand.nextBoolean());
//            DBManager.addTask(temp);
//        }
        req.setAttribute("tasks", DBManager.getAllTasks());
        req.getRequestDispatcher("/sprint/show-tasks.jsp").forward(req, resp);
    }
}
