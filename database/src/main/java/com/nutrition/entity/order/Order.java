package com.nutrition.entity.order;

import com.nutrition.entity.user.User;
import com.nutrition.entity.util.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "open_date")
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

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<OrderContent> orderContent = new ArrayList<>();

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "close_date")
    private LocalDateTime closeDate;

    public Order(User user, Delivery delivery) {
        this.user = user;
        this.delivery = delivery;
    }

    public void addOrderContent(OrderContent contentToAdd) {
        orderContent.add(contentToAdd);
    }

    public void removeOrderContent(OrderContent contentToDelete) {
        int indexOfProduct = orderContent.indexOf(contentToDelete);
        orderContent.remove(indexOfProduct);
    }
}
