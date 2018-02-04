package entity.order;

import entity.user.User;
import entity.util.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(
//            name = "orders_products",
//            joinColumns = @JoinColumn(name = "order_id")
//    )
//    @MapKeyJoinColumn(name = "product_id", referencedColumnName = "id")
//    @Column(name = "product_qty")
//    private Map<Product, Integer> products = new HashMap<>();

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderContent> orderContent = new ArrayList<>();

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "close_date")
    private LocalDateTime closeDate;
}
