package mik.kwi.egz1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id", nullable = false)
    private Integer orderId;
    @Column(name = "date_of_order")
    private LocalDateTime dateOfOrder;
    @OneToMany(mappedBy = "order")
    private List<Pizza> pizzas;
}
