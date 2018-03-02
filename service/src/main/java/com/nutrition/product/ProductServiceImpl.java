package com.nutrition.product;

import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import com.nutrition.repository.product.ProductRepository;
import com.nutrition.util.ProductSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author a.shestovsky
 */

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public Page<Product> findAll(int pageNumber, int qtyOnPage) {
        return productRepository.findAll(new PageRequest(pageNumber, qtyOnPage));
    }

//    @Override
//    public void update(Product product) {
//        productRepository.update(product);
//    }

    @Override
    public Product findByTitle(String title) {
        return productRepository.findByTitleContaining(title);
    }

    @Override
    public List<Product> findAllByCategory(Long categoryId) {
        return productRepository.findAllByCategory_Id(categoryId);
    }

    @Override
    public List<Product> findByTitleCategoryBrands(String title, Category category, List<Brand> brands) {
        return productRepository.findByTitleCategoryBrands(title, category, brands);
    }

    @Override
    public List<Product> findByTitleCategoryBrandsViaId(String title, Long categoryId, List<Long> brandIds,
                                                        int pageNumber, int productsOnPage) {
        int offset = productsOnPage * (pageNumber - 1);
        return productRepository.findByTitleCategoryBrandsViaId(title, categoryId, brandIds, productsOnPage, offset);
    }

    @Override
    public Page<Product> findByFilter(ProductSearchFilter productSearchFilter, int pageNumber, int qtyOnPage) {

        return null;
    }

    @Override
    public int countPagesByFilter(ProductSearchFilter productSearchFilter) {

        return 10;
    }
}
