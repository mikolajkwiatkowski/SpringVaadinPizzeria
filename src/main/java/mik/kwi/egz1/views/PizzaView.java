package mik.kwi.egz1.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import mik.kwi.egz1.controller.PizzaController;
import mik.kwi.egz1.model.Pizza;
import mik.kwi.egz1.repositories.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Route("pizza")
@Component
public class PizzaView extends VerticalLayout {
    private final PizzaRepo pizzaRepo;
    private final PizzaController pizzaController;

    private TextField nazwaField = new TextField("Nazwa");
    private TextField cenaField= new TextField("Cena");
    private TextField skladnikiField = new TextField("Składniki");

    private Grid<Pizza> pizzaGrid = new Grid<>(Pizza.class);

    private Pizza selectedPizza;


    @Autowired
    public PizzaView(PizzaRepo pizzaRepo, PizzaController pizzaController){
        this.pizzaRepo = pizzaRepo;
        this.pizzaController = pizzaController;
        initLayout();

    }
    private void initLayout() {
        // Napis "Pizzeria" z hiperłączem do /main
        Span mainLink = new Span("Pizzeria");
        mainLink.addSingleClickListener(event->navigateTo(""));
        mainLink.getStyle().set("font-size", "24px");
        mainLink.getStyle().set("font-weight", "bold");
        mainLink.getStyle().set("color", "black");
        mainLink.getStyle().set("text-decoration", "none");
        add(mainLink,nazwaField,cenaField,skladnikiField);
        Button saveButton = new Button("Zapisz");

        Button deleteButton = new Button("Usuń");

        add(saveButton, deleteButton);


        pizzaGrid.setItems(pizzaRepo.findAll());
        pizzaGrid.removeAllColumns();
        pizzaGrid.addColumn(Pizza::getNazwa).setHeader("Name");
        pizzaGrid.addColumn(Pizza::getPrice).setHeader("Price");
        pizzaGrid.addColumn(Pizza::getIngredients).setHeader("Ingredients");
        add(pizzaGrid);

    }

    private void navigateTo(String route) {
        getUI().ifPresent(ui -> ui.navigate(route));
    }
}
