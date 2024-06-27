package mik.kwi.egz1.services;


import mik.kwi.egz1.model.Pizza;
import mik.kwi.egz1.repositories.PizzaRepo;
import org.springframework.stereotype.Service;
import mik.kwi.egz1.model.OrderedPizza;
import mik.kwi.egz1.repositories.OrderedPizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderedPizzaServiceImp implements OrderedPizzaService {


    OrderedPizzaRepo orderedPizzaRepo; //es
    @Autowired
    public OrderedPizzaServiceImp(OrderedPizzaRepo orderedPizzaRepo)
    {
        this.orderedPizzaRepo=orderedPizzaRepo;
    }
    @Override
    public Optional<OrderedPizza> getOrderedPizza(Integer ordered_pizza_id){
        return orderedPizzaRepo.findById(ordered_pizza_id);
    }
    @Override
    public OrderedPizza setOrderedPizza(OrderedPizza pizza)
    {
        return orderedPizzaRepo.save(pizza);
    }
    @Override
    public void deleteOrderedPizza(OrderedPizza pizza)
    {
        orderedPizzaRepo.delete(pizza);
    }

    @Override
    public Page<OrderedPizza> getOrderedPizzas(Pageable pageable)
    {
        return orderedPizzaRepo.findAll(pageable);
    }
}
