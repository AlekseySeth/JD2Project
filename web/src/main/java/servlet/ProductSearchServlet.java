package servlet;

import entity.product.Brand;
import entity.product.Category;
import service.DataListService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/product-search-bar")
public class ProductSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataListService dataListService = DataListService.newInstance();
        List<Category> categories = dataListService.getAllCategories();
        List<Brand> brands = dataListService.getAllBrands();
        req.setAttribute("categories", categories);
        req.setAttribute("brands", brands);
        req.getServletContext().getRequestDispatcher(getPath("product-search-bar")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
