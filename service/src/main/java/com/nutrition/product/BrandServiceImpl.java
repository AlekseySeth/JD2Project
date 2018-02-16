package com.nutrition.product;

import com.nutrition.dao.product.BrandDao;
import com.nutrition.entity.product.Brand;
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

    private final BrandDao brandDao;

    @Autowired
    public BrandServiceImpl(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    @Override
    public Brand findById(Long id) {
        return brandDao.findById(id);
    }

    @Override
    public List<Brand> findAll() {
        return brandDao.findAll();
    }
}
