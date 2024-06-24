package mik.kwi.egz1.services;

import mik.kwi.egz1.model.Pizza;
import mik.kwi.egz1.repositories.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PizzaServiceImp implements PizzaService {

    PizzaRepo pizzaRepo; //es
    @Autowired
    public PizzaServiceImp(PizzaRepo pizzaRepo)
    {
        this.pizzaRepo=pizzaRepo;
    }
    @Override
    public Optional<Pizza> getPizza(Integer pizza_id){
        return pizzaRepo.findById(pizza_id);
    }
    @Override
    public Pizza setPizza(Pizza pizza)
    {
        return pizzaRepo.save(pizza);
    }
    @Override
    public void deletePizza(Pizza pizza)
    {
        pizzaRepo.delete(pizza);
    }

    @Override
    public Page<Pizza> getPizzas(Pageable pageable)
    {
        return pizzaRepo.findAll(pageable);
    }
}
