package entity.order;

import entity.product.Product;
import entity.user.User;
import entity.util.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author a.shestovsky
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends IdentifiableEntity {

    @Column
    private LocalDateTime openDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

//    @ManyToMany
//    @JoinTable(
//            name = "orders_products",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_qty")
//    )
//    @MapKeyJoinColumn(name = "product_id")

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    @Column(name = "product_qty")
    @MapKeyJoinColumn(name = "product_id", referencedColumnName = "id")
    private Map<Product, Integer> products = new HashMap<>();

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "close_date")
    private LocalDateTime closeDate;

    @Transient
    public void addProduct(Product product, int qty) {
        products.put(product, qty);
    }

    @Transient
    public void removeProduct(Product product, int qty) {
        products.remove(product, qty);
    }
}
