package mik.kwi.egz1.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import mik.kwi.egz1.model.Orders;
import mik.kwi.egz1.model.OrderedPizza;
import mik.kwi.egz1.repositories.OrdersRepo;
import mik.kwi.egz1.repositories.OrderedPizzaRepo;
import mik.kwi.egz1.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Route("KucharzPanel")
@Component
public class KucharzPanelView extends VerticalLayout {

    private final OrdersRepo ordersRepo;
    private final OrderedPizzaRepo orderedPizzaRepo;
    private final OrdersService ordersService;

    private final Grid<Orders> zamowienieGrid = new Grid<>();
    private final Grid<OrderedPizza> zamowionePizzeGrid = new Grid<>();

    @Autowired
    public KucharzPanelView(OrdersRepo ordersRepo, OrderedPizzaRepo orderedPizzaRepo, OrdersService ordersService){
        this.ordersRepo = ordersRepo;
        this.orderedPizzaRepo = orderedPizzaRepo;
        this.ordersService = ordersService;
        initLayout();
    }

    private void initLayout() {
        VerticalLayout layoutPizzy = new VerticalLayout();
        VerticalLayout layoutGuziki = new VerticalLayout();
        Select<String> selectPoCzymSzukac = new Select<>();

        selectPoCzymSzukac.setLabel("Szukaj po:");
        selectPoCzymSzukac.setItems("Adres", "Numer telefonu");
        selectPoCzymSzukac.setValue("Adres");

        zamowienieGrid.addColumn(Orders::getOrderId).setHeader("Order_ID");
        zamowienieGrid.addColumn(Orders::getStatusOfOrder).setHeader("Status");
        zamowienieGrid.addColumn(Orders::getAddress).setHeader("Adres");
        zamowienieGrid.addColumn(Orders::getTelephoneNumber).setHeader("NrTelefonu");

        zamowionePizzeGrid.addColumn(OrderedPizza::getOrderedPizzaId).setHeader("OrderedPizzaId");
        zamowionePizzeGrid.addColumn(OrderedPizza::getSize).setHeader("Size");
        zamowionePizzeGrid.addColumn(OrderedPizza::getPrice).setHeader("Price");

        loadData();

        TextField searchTextField = new TextField("Podaj " + selectPoCzymSzukac.getValue());
        Button guz1 = new Button("Szukaj");
        if(selectPoCzymSzukac.getValue()=="Adres"){
            guz1.addClickListener(event -> searchByAdres(zamowienieGrid, searchTextField.getValue()));
        }
        else if(selectPoCzymSzukac.getValue()=="NrTelefonu"){
            guz1.addClickListener(event -> searchByNrTelefonu(zamowienieGrid, searchTextField.getValue()));
        }
        Button guz2 = new Button("Usuń");

        add(layoutPizzy, layoutGuziki);
        layoutGuziki.add(searchTextField, guz1, guz2);
        layoutPizzy.add(zamowienieGrid);
        layoutPizzy.add(zamowionePizzeGrid);

        // Podwójne kliknięcie na wiersz w gridzie
        zamowienieGrid.addItemDoubleClickListener(event -> openEditDialog(event.getItem()));
    }

    private void loadData() {
        List<Orders> orders = ordersRepo.findAll();
        List<OrderedPizza> orderedPizzas = orderedPizzaRepo.findAll();
        zamowienieGrid.setItems(orders);
        zamowionePizzeGrid.setItems(orderedPizzas);
    }

    private void searchByAdres(Grid<Orders> orderGrid, String adres) {
        List<Orders> ordersByAdres = ordersRepo.findOrderByAdres(adres);
        orderGrid.setItems(ordersByAdres);
    }
    private void searchByNrTelefonu(Grid<Orders> orderGrid, String nrTelefonu) {
        List<Orders> ordersByNrTelefonu = ordersRepo.findOrderByNumerTelefonu(nrTelefonu);
        orderGrid.setItems(ordersByNrTelefonu);
    }
    private void openEditDialog(Orders order) {
        Dialog dialog = new Dialog();
        dialog.setWidth("400px");
        dialog.setHeight("300px");

        VerticalLayout dialogLayout = createDialogLayout(order, dialog);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private VerticalLayout createDialogLayout(Orders order, Dialog dialog) {
        TextField statusField = new TextField("Status");
        statusField.setValue(order.getStatusOfOrder());

        TextField addressField = new TextField("Adres");
        addressField.setValue(order.getAddress());

        TextField telephoneField = new TextField("NrTelefonu");
        telephoneField.setValue(order.getTelephoneNumber());

        Button saveButton = new Button("Save", event -> {
            order.setStatusOfOrder(statusField.getValue());
            order.setAddress(addressField.getValue());
            order.setTelephoneNumber(telephoneField.getValue());
            ordersRepo.save(order);
            dialog.close();
            loadData(); // Reload the grid data
        });

        Button cancelButton = new Button("Cancel", event -> dialog.close());

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);

        VerticalLayout dialogLayout = new VerticalLayout(statusField, addressField, telephoneField, buttonsLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(Alignment.STRETCH);
        return dialogLayout;
    }
}
