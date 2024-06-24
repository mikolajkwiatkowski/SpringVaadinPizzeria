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
        loadData();
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
        saveButton.addClickListener(event -> savePizza());
        Button deleteButton = new Button("Usuń");
        deleteButton.addClickListener(event -> deletePizza());
        add(saveButton, deleteButton);


        pizzaGrid.setItems(pizzaRepo.findAll());
        pizzaGrid.removeAllColumns();
        pizzaGrid.addColumn(Pizza::getName).setHeader("Name");
        pizzaGrid.addColumn(Pizza::getPrice).setHeader("Price");
        pizzaGrid.addColumn(Pizza::getIngredients).setHeader("Ingredients");
        pizzaGrid.asSingleSelect().addValueChangeListener(event -> editPizza(event.getValue()));
        add(pizzaGrid);

    }
    private void savePizza() {
        if (selectedPizza != null) {
            selectedPizza.setName(nazwaField.getValue());
            selectedPizza.setIngredients(skladnikiField.getValue());
            selectedPizza.setPrice(Double.valueOf(cenaField.getValue()));
            pizzaController.updatePizza(selectedPizza.getPizzaId(), selectedPizza);
        } else {
            Pizza newPizza = new Pizza();
            newPizza.setName(nazwaField.getValue());
            newPizza.setIngredients(skladnikiField.getValue());
            newPizza.setPrice(Double.valueOf(cenaField.getValue()));
            pizzaController.createPizza(newPizza);
        }
        loadData();
        clearForm();
    }

    private void editPizza(Pizza pizza){
        if(pizza==null){
            clearForm();
        }
        selectedPizza=pizza;
        nazwaField.setValue(pizza.getName());

        skladnikiField.setValue(pizza.getIngredients());

        cenaField.setValue(String.valueOf(pizza.getPrice()));

    }


    private void deletePizza() {
        if (selectedPizza != null) {
            pizzaController.deletePizza(selectedPizza.getPizzaId(), selectedPizza);
        }
        loadData();
        clearForm();
    }
    private void clearForm() {
        selectedPizza = null;
        nazwaField.clear();
        skladnikiField.clear();
        cenaField.clear();
    }



    private void loadData() {
        pizzaGrid.setItems(pizzaRepo.findAll());
    }
    private void navigateTo(String route) {
        getUI().ifPresent(ui -> ui.navigate(route));
    }
}
