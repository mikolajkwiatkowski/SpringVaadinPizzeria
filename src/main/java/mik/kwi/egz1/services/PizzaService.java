package mik.kwi.egz1.services;

import mik.kwi.egz1.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PizzaService {
    Optional<Pizza> getPizza(Integer pizza_id);
    Pizza setPizza(Pizza pizza);
    void deletePizza(Pizza pizza);
    Page<Pizza> getPizzas(Pageable pageable);

}
