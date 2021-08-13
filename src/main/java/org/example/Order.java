package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order{

    @EmbeddedId
    private OrderKey orderKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", insertable = false, updatable = false)
    private Buyer buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "price")
    private int price;

    public Order(OrderKey orderKey, int price) {
        this.orderKey = orderKey;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order: " +
                "orderKey=" + orderKey +
                ", price=" + price;
    }
}
