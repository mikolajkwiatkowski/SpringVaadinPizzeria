package mik.kwi.egz1.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderedPizza")
@Data
public class OrderedPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderedPizzaId")
    private Integer orderedPizzaId;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private double price;


}
