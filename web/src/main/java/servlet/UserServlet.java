package servlet;

import entity.user.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author a.shestovsky
 */

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        User userById = userService.getUserById(1L);
        req.setAttribute("user", userById);
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/html/user.jsp")
                .forward(req, resp);
    }
}
