package mik.kwi.egz1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Pizzas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pizza_id", nullable = false)
    private Integer pizzaId;
    @Column(name="Name",length = 50)
    private String name;
    @Column(name="Price")
    private Double price;
    @Column(name="Ingredients",length = 300)
    private String ingredients;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
