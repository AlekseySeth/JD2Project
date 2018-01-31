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
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "promotions")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BasicPromo extends IdentifiableEntity {

    @Column(name = "name", unique = true, nullable = false)
    protected String name;

    @Column(name = "is_active")
    protected boolean isActive;

    protected Set<Product> promotedProducts = new HashSet<>();

}
