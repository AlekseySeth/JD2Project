package com.nutrition.entity.marketing;

import com.nutrition.entity.product.Product;
import com.nutrition.entity.util.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private String name;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.EAGER)
    private List<Product> promotedProducts = new ArrayList<>();

    protected Promotion(String name, boolean isActive) {
        this.name = name;
        this.active = isActive;
    }
}
