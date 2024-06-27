package mik.kwi.egz1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Pizza")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pizzaId")
    private Integer pizzaId;
    @Column(name="Nazwa")
    private String nazwa;
    @Column(name="Price")
    private Double price;
    @Column(name="Rozmiar")
    private String rozmiar;
    @Column(name="Ingredients")
    private String ingredients;


}
