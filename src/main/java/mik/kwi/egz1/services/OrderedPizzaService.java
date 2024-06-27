package mik.kwi.egz1.services;

import mik.kwi.egz1.model.OrderedPizza;
import mik.kwi.egz1.model.Pizza;
import mik.kwi.egz1.repositories.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderedPizzaService {
    Optional<OrderedPizza> getOrderedPizza(Integer ordered_pizza_id);
    OrderedPizza setOrderedPizza(OrderedPizza orderedPizza);
    void deleteOrderedPizza(OrderedPizza orderedPizza);
    Page<OrderedPizza> getOrderedPizzas(Pageable pageable);

}

