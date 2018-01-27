package entity.product;

import entity.util.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author a.shestovsky
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends IdentifiableEntity {

    @Column(name = "title", unique = false, nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "qty_in_stock", nullable = false)
    private int qtyInStock;

    @ManyToOne
    @Column(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @Column(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "image_url")
    private String imageURL;
}
