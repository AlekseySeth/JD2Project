package com.nutrition.product;

import com.nutrition.entity.product.Brand;
import com.nutrition.repository.product.BrandRepository;
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
@CacheConfig(cacheNames = "brands")
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findOne(id);
    }

    @Override
    @Cacheable
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
