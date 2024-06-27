package mik.kwi.egz1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Ingredient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ingredientId", nullable = false)
    private Integer ingredientId;
    @Column(name="name",length = 50)
    private String name;
    @Column(name="price")
    private Double price;



}
