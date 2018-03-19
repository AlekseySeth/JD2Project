package com.nutrition.product;

import com.nutrition.entity.product.Category;
import com.nutrition.repository.product.CategoryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author a.shestovsky
 */

@Service
@Transactional
@NoArgsConstructor
@CacheConfig(cacheNames = "categories")
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    @Cacheable
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
