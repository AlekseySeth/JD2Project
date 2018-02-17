package com.nutrition.product;

import com.nutrition.entity.product.Brand;
import com.nutrition.repository.product.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author a.shestovsky
 */

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findOne(id);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
