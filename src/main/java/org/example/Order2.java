package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order2")
public class Order2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price")
    private int price;

    public Order2(Buyer buyer, Product product, int price) {
        this.buyer = buyer;
        this.product = product;
        this.price = price;
    }

}
