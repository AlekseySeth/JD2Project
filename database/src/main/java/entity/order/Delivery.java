package entity.order;

import entity.util.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author a.shestovsky
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "deliveries")
public class Delivery extends IdentifiableEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;
}
