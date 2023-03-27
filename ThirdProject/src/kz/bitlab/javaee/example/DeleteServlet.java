package kz.bitlab.javaee.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Stack;

@WebServlet(value = "/example/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = "/example/home?error";
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("item-id"));
        } catch (NumberFormatException e) {}

        if (id != null) {
            if (DBManager.deleteItem(id)) {
                redirect = "/example/home?error";
            }
        }

        resp.sendRedirect(redirect);
    }
}
