package com.nutrition.product;

import com.nutrition.dto.ProductDto;
import com.nutrition.entity.marketing.Promotion;
import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import com.nutrition.marketing.PromotionService;
import com.nutrition.repository.product.ProductRepository;
import com.nutrition.util.ProductSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author a.shestovsky
 */

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final int ONE = 1;
    private static final String DEFAULT_IMAGE_URL = "/resources/images/default.png";
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
            imageURL = DEFAULT_IMAGE_URL;
        }
        product.setImageURL(imageURL);
        productRepository.save(product);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
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
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void update(Product product, String title, String description, BigDecimal price, Long promoId,
                       int qtyInStock, String imageURL, Long categoryId, Long brandId) throws OptimisticLockException {
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        if (promoId != null) {
            Promotion promotion = promotionService.findById(promoId);
            product.setPromotion(promotion);
        } else {
            product.setPromotion(null);
        }
        product.setQtyInStock(qtyInStock);
        if ("".equals(imageURL)) {
            imageURL = DEFAULT_IMAGE_URL;
        }
        product.setImageURL(imageURL);
        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        Brand brand = brandService.findById(brandId);
        product.setBrand(brand);
        try {
            productRepository.save(product);
        } catch (ObjectOptimisticLockingFailureException ole) {
            throw new OptimisticLockException("OptimisticLockException during product update!");
        }
    }
}
