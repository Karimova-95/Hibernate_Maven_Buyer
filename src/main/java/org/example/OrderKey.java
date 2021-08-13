package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Embeddable
public class OrderKey implements Serializable{

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "product_id")
    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderKey orderKey = (OrderKey) o;
        return Objects.equals(buyerId, orderKey.buyerId) && Objects.equals(productId, orderKey.productId);
    }

    public OrderKey(Long buyerId, Long productId) {
        this.buyerId = buyerId;
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerId, productId);
    }

    @Override
    public String toString() {
        return "OrderKey: " +
                "buyerId=" + buyerId +
                ", productId=" + productId;
    }
}
