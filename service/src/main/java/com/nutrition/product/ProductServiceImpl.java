package com.nutrition.product;

import com.nutrition.dto.ProductDto;
import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import com.nutrition.marketing.PromotionService;
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
    private final PromotionService promotionService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, BrandService brandService, PromotionService promotionService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.promotionService = promotionService;
    }

    @Override
    public void save(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQtyInStock(productDto.getQtyInStock());
        Category category = categoryService.findById(productDto.getCategoryId());
        product.setCategory(category);
        Brand brand = brandService.findById(productDto.getBrandId());
        product.setBrand(brand);
        String imageURL = productDto.getImageURL();
        if ("".equals(imageURL) || imageURL == null) {
            imageURL = "/resources/images/default.png";
        }
        product.setImageURL(imageURL);
        productRepository.save(product);
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
    public List<Product> findProductsByFilter(ProductSearchFilter productSearchFilter, Integer pageNumber, Integer qtyOnPage) {
        String searchTitle = productSearchFilter.getSearchTitle();
        Long searchCategoryId = productSearchFilter.getSearchCategoryId();
        List<Long> searchBrandsId = productSearchFilter.getSearchBrandsId();
        int offset = (pageNumber - ONE) * qtyOnPage;
        return productRepository.findProductsByFilter(searchTitle, searchCategoryId, searchBrandsId, qtyOnPage, offset);
    }

    @Override
    public Integer countPagesByFilter(ProductSearchFilter productSearchFilter, Integer qtyOnPage) {
        String searchTitle = productSearchFilter.getSearchTitle();
        Long searchCategoryId = productSearchFilter.getSearchCategoryId();
        List<Long> searchBrandsId = productSearchFilter.getSearchBrandsId();
        long productsCount = productRepository.countProductsByFilter(searchTitle, searchCategoryId, searchBrandsId);
        return (int) Math.ceil((double) productsCount / qtyOnPage);
    }

    @Override
    public void update(Product product, String title, String description, BigDecimal price, Long promoId,
                       int qtyInStock, String imageURL, Long categoryId, Long brandId) {
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setPromotion(promotionService.findById(promoId));
        product.setQtyInStock(qtyInStock);
        if ("".equals(imageURL)) {
            imageURL = null;
        }
        product.setImageURL(imageURL);
        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        Brand brand = brandService.findById(brandId);
        product.setBrand(brand);
        productRepository.save(product);
    }
}
