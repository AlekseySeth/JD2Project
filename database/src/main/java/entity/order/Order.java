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
    private LocalDate openDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @ManyToMany
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_qty")
    )
    @MapKeyJoinColumn(name = "product_id")
    private Map<Product, Integer> products = new HashMap<>();

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "close_date")
    private LocalDate closeDate;
}
