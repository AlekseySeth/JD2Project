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
@Table(name = "fixed_price_promos")
@PrimaryKeyJoinColumn(name = "promo_id")
public class FixedPricePromotion extends Promotion {

    @Column(name = "fixed_price", nullable = false)
    private BigDecimal fixedPrice;

    public FixedPricePromotion(String name, boolean isActive, BigDecimal fixedPrice) {
        super(name, isActive);
        this.fixedPrice = fixedPrice;
    }
}
