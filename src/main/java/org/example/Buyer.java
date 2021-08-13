package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "buyer")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Order> orders;
//    private List<Order2> orders;

    @Override
    public String toString() {
        return "Buyer: " +
                "id=" + id +
                ", name='" + name;
    }
}
