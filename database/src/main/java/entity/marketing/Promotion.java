package entity.marketing;

import entity.product.Product;
import entity.util.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "promotions")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Promotion extends IdentifiableEntity {

    @Column(name = "name", unique = true, nullable = false)
    protected String name;

    @Column(name = "is_active")
    protected boolean isActive;

    @OneToMany(mappedBy = "promotion")
    protected List<Product> promotedProducts = new ArrayList<>();

    protected Promotion(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }
}
