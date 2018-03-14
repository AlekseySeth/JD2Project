package com.nutrition.product;

import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import com.nutrition.repository.product.ProductRepository;
import com.nutrition.util.ProductSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author a.shestovsky
 */

@Service
@Transactional
@CacheConfig(cacheNames = "products")
public class ProductServiceImpl implements ProductService {

    private static final int ONE = 1;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, BrandService brandService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Cacheable
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Product> findProductsByFilter(ProductSearchFilter productSearchFilter, int pageNumber, int qtyOnPage) {
        String searchTitle = productSearchFilter.getSearchTitle();
        Long searchCategoryId = productSearchFilter.getSearchCategoryId();
        List<Long> searchBrandsId = productSearchFilter.getSearchBrandsId();
        int offset = (pageNumber - ONE) * qtyOnPage;
        return productRepository.findProductsByFilter(searchTitle, searchCategoryId, searchBrandsId, qtyOnPage, offset);
    }

    @Override
    public int countPagesByFilter(ProductSearchFilter productSearchFilter, int qtyOnPage) {
        String searchTitle = productSearchFilter.getSearchTitle();
        Long searchCategoryId = productSearchFilter.getSearchCategoryId();
        List<Long> searchBrandsId = productSearchFilter.getSearchBrandsId();
        long productsCount = productRepository.countProductsByFilter(searchTitle, searchCategoryId, searchBrandsId);
        return (int) Math.ceil((double) productsCount / qtyOnPage);
    }

    @Override
    public void update(Product product, String title, String description, BigDecimal price,
                       int qtyInStock, String imageURL, Long categoryId, Long brandId) {
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setQtyInStock(qtyInStock);
        product.setImageURL(imageURL);
        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        Brand brand = brandService.findById(brandId);
        product.setBrand(brand);
        productRepository.save(product);
    }
}
