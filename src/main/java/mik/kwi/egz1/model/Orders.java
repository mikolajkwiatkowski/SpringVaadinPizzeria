package mik.kwi.egz1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="orderId", nullable = false)
    private Integer orderId;

    @Column(name = "dateOfOrder")
    private LocalDateTime dateOfOrder;

    @Column(name = "statusOfOrder")
    private String statusOfOrder;

    @Column(name = "address")
    private String address;

    @Column(name = "telephoneNumber")
    private String telephoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Orders_and_OrderedPizza",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "ordered_pizza_id")
    )
    private List <Pizza> orderedPizzas = new ArrayList<>();

}