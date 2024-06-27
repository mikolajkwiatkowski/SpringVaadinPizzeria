package mik.kwi.egz1.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

@Route("UserPanel")
@Component
public class UserPanelView extends VerticalLayout {
    public UserPanelView() {
        initLayout();
    }

    public void initLayout(){
        Span mainLink = new Span("Pizzeria");
        mainLink.addSingleClickListener(event->navigateTo(""));
        mainLink.getStyle().set("font-size", "24px");
        mainLink.getStyle().set("font-weight", "bold");
        mainLink.getStyle().set("color", "black");
        mainLink.getStyle().set("text-decoration", "none");

        Button zamowSwojaButton = new Button("Stworz swoja pizze");
        Button zamowNaszaButton = new Button("Wybierz z gotowych pizz");
        Button sprawdzStatusButton = new Button("Sprawdz status zamowienia");

        zamowSwojaButton.addClickListener(event -> navigateTo("swoja_pizza"));
        zamowNaszaButton.addClickListener(event -> navigateTo("nasze_pizze"));
        sprawdzStatusButton.addClickListener(event -> navigateTo("status"));


        add(zamowSwojaButton,zamowNaszaButton,sprawdzStatusButton);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void navigateTo(String route) {
        getUI().ifPresent(ui -> ui.navigate(route));
    }
}
