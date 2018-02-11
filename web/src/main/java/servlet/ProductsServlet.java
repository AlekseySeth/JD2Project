package servlet;

import entity.product.Brand;
import entity.product.Category;
import entity.product.Product;
import service.DataListService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.ServletUtil.getPath;

@WebServlet(urlPatterns = "/products")
public class ProductsServlet extends HttpServlet {

    private static final int DEFAULT_NUMBER_OF_PRODUCTS = 3;
    private static final int FIVE = 5;
    private static final int TEN = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Integer> pagesList = Arrays.asList(DEFAULT_NUMBER_OF_PRODUCTS, FIVE, TEN);
        req.setAttribute("pagesList", pagesList);
        DataListService dataListService = DataListService.newInstance();
        List<Category> categories = dataListService.getAllCategories();
        List<Brand> brands = dataListService.getAllBrands();
        req.setAttribute("categories", categories);
        req.setAttribute("brands", brands);

        req.getServletContext().getRequestDispatcher(getPath("products")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String categoryIdString = req.getParameter("category");
        Long categoryId = null;
        if (categoryIdString != null && !"all".equals(categoryIdString)) {
            categoryId = Long.valueOf(categoryIdString);
            session.setAttribute("selectedCategory", categoryId);
        } else if ("all".equals(categoryIdString)) {
            session.setAttribute("selectedCategory", 0);
        }
        String title = req.getParameter("title");
        if (title != null && "".equals(title)) {
            title = null;
        }
        String[] brandsString = req.getParameterValues("brand");
        List<Long> brandsId = new ArrayList<>();
        if (brandsString != null) {
            for (String currentBrandString : brandsString) {
                brandsId.add(Long.valueOf(currentBrandString));
            }
        }
        String productsOnPageString = req.getParameter("productsOnPage");
        Integer productsOnPage = DEFAULT_NUMBER_OF_PRODUCTS;
        if (productsOnPageString != null) {
            productsOnPage = Integer.valueOf(productsOnPageString);
            session.setAttribute("selectedProductsOnPage", productsOnPage);
        }

        Integer page = Integer.valueOf(req.getParameter("page"));
        session.setAttribute("selectedPage", page);


        int offset = productsOnPage * (page - 1);

        List<Product> products = ProductService.newInstance()
                .searchProducts(categoryId, title, brandsId, productsOnPage, offset);

        session.setAttribute("products", products);
        resp.sendRedirect("/products?page=" + page);
    }
}
