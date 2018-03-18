package com.nutrition.repository.marketing;

import com.nutrition.entity.marketing.Promotion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface PromotionRepository extends CrudRepository<Promotion, Long> {

    List<Promotion> findAll();
}
