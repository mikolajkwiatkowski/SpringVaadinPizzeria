package mik.kwi.egz1.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import mik.kwi.egz1.model.Pizza;
import mik.kwi.egz1.repositories.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Route("swoja_pizza")
@Component
public class OwnPizzaView extends VerticalLayout {
    private final PizzaRepo pizzaRepo;
    @Autowired
    public OwnPizzaView(PizzaRepo pizzaRepo){

        this.pizzaRepo = pizzaRepo;
        initLayout();
    }

    private void initLayout()
    {

    }
}