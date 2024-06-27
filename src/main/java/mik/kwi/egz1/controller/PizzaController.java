package mik.kwi.egz1.controller;


import jakarta.validation.Valid;
import mik.kwi.egz1.model.Pizza;
import mik.kwi.egz1.services.PizzaService;
import mik.kwi.egz1.services.PizzaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
    private static PizzaService pizzaService = null;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        PizzaController.pizzaService = pizzaService;
    }

//    @GetMapping("/{pizza_id}")
//    public ResponseEntity<Pizza> getPizza(@PathVariable Integer pizza_id) {
//        Optional<Pizza> pizza = pizzaService.getPizza(pizza_id);
//        return ResponseEntity.of(pizza);
//    }

    @GetMapping
    public static Page<Pizza> getPizzas(Pageable pageable) {
        return pizzaService.getPizzas(pageable);
    }

    @PostMapping
    public ResponseEntity<Void> createPizza(@Valid @RequestBody Pizza pizza) {
        Pizza createdPizza = pizzaService.setPizza(pizza);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{pizza_id}").buildAndExpand(createdPizza.getPizzaId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{pizza_id}")
    public ResponseEntity<Void> updatePizza(@PathVariable Integer pizza_id, @Valid @RequestBody Pizza pizza) {
        Optional<Pizza> existingPizza = pizzaService.getPizza(pizza_id);
        if (existingPizza.isPresent()) {
            pizzaService.setPizza(pizza);
            return ResponseEntity.ok().build(); // 200
        } else {
            return ResponseEntity.notFound().build(); // 404 - Not found
        }
    }

    @DeleteMapping("/{pizza_id}")
    public ResponseEntity<Void> deletePizza(@PathVariable Integer pizza_id, @Valid @RequestBody Pizza pizza) {
        Optional<Pizza> existingPizza = pizzaService.getPizza(pizza_id);
        if (existingPizza.isPresent()) {
            pizzaService.deletePizza(pizza);
            return ResponseEntity.ok().build(); // 200
        } else {
            return ResponseEntity.notFound().build(); // 404 - Not found
        }
    }

}
