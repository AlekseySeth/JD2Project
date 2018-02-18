package servlet;

import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import com.nutrition.product.BrandService;
import com.nutrition.product.CategoryService;
import com.nutrition.product.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

import static util.AppContextContainer.getContext;
import static util.ServletUtil.getPath;

@WebServlet(urlPatterns = "/products")
public class ProductsServlet extends HttpServlet {

    private AnnotationConfigApplicationContext context;
    private static final int TEN_PRODUCTS_ON_PAGE = 10;
    private static final int FIVE_PRODUCTS_ON_PAGE = 5;
    private static final int THREE_PRODUCTS_ON_PAGE = 3;

    @Override
    public void init() throws ServletException {
        context = getContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Integer> pagesList = Arrays.asList(TEN_PRODUCTS_ON_PAGE, FIVE_PRODUCTS_ON_PAGE, THREE_PRODUCTS_ON_PAGE);
        req.setAttribute("pagesList", pagesList);
        CategoryService categoryService = context.getBean(CategoryService.class);
        BrandService brandService = context.getBean(BrandService.class);

        List<Category> categories = categoryService.findAll();
        List<Brand> brands = brandService.findAll();
        req.setAttribute("categories", categories);
        req.setAttribute("brands", brands);

        req.getServletContext().getRequestDispatcher(getPath("products")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = context.getBean(ProductService.class);
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
        List<Long> brandIds = new ArrayList<>();
        if (brandsString != null) {
            for (String currentBrandString : brandsString) {
                brandIds.add(Long.valueOf(currentBrandString));
            }
            session.setAttribute("selectedBrands", brandIds);
        }

        String productsOnPageString = req.getParameter("productsOnPage");
        Integer productsOnPage = TEN_PRODUCTS_ON_PAGE;
        if (productsOnPageString != null) {
            productsOnPage = Integer.valueOf(productsOnPageString);
            session.setAttribute("selectedProductsOnPage", productsOnPage);
        }

        Integer pageNumber = Integer.valueOf(req.getParameter("page"));
        session.setAttribute("selectedPage", pageNumber);

        List<Product> products = productService
                .findByTitleCategoryBrandsViaId(title, categoryId, brandIds, pageNumber, productsOnPage);

        session.setAttribute("products", products);
        resp.sendRedirect("/products?page=" + pageNumber);
    }
}
