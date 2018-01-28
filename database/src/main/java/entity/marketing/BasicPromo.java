package entity.marketing;

import entity.product.Product;
import entity.util.IdentifiableEntity;

import javax.persistence.MappedSuperclass;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class BasicPromo extends IdentifiableEntity {

    protected String name;
    protected boolean isActive;

    protected Set<Product> promotedProdects = new HashSet<>();

}
