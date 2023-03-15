package task3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/task3/news")
public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        News newNews = new News(null,
                "Rayan find that no mashed potatoes left for him!",
                "Today Rayan wen to the nearest foodcourt and found out that there was no mashed potatoes left! It was devastating news for him. He walked all the way for nothing!",
                "Asset"
                );
        if (DBManager.getNewsList().isEmpty()) {
            for (int i = 0; i < 10; i++) {
                DBManager.addNews(newNews);
            }
        }

        req.setAttribute("newsList", DBManager.getNewsList());
        req.getRequestDispatcher("/task3/news.jsp").forward(req, resp);
    }
}
