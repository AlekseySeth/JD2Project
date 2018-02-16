package com.nutrition.dao.order;

import com.nutrition.dao.common.GenericDaoImpl;
import com.nutrition.entity.order.Delivery;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class DeliveryDaoImpl extends GenericDaoImpl<Delivery> implements DeliveryDao {

}
