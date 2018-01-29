package servlet;

import service.DataListService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.ServletUtil.getPath;

@WebServlet(name = "/data-list")
public class DataListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataListService dataListService = DataListService.newInstance();

        req.setAttribute("users", dataListService.getAllUsers());
        req.setAttribute("products", dataListService.getAllProducts());
        req.setAttribute("categories", dataListService.getAllCategories());
        req.setAttribute("brands", dataListService.getAllBrands());
        req.setAttribute("deliveries", dataListService.getAllDeliveries());

        req.getServletContext()
                .getRequestDispatcher(getPath("data-list"))
                .forward(req, resp);
    }
}
