package entity.marketing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "percentage_discount_promos")
@PrimaryKeyJoinColumn(name = "promo_id")
public class PercentageDiscountPromo extends BasicPromo {

    @Column(name = "discount_value", nullable = false)
    private BigDecimal discountValue;

}
