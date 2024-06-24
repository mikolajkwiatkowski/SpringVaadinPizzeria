package mik.kwi.egz1.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.stereotype.Component;

@Route("")
@Component
public class MainView extends VerticalLayout {
    public MainView() {
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
        // Utworzenie przycisków
        Button userButton = new Button("Panel użytkownika");
        Button adminButton = new Button("Panel admina");

        // Ustawienia przycisków
        userButton.setWidth("300px");
        userButton.setHeight("100px");
        adminButton.setWidth("300px");
        adminButton.setHeight("100px");

        // Dodanie click listenerów
        userButton.addClickListener(event -> navigateTo("userPanel"));
        adminButton.addClickListener(event -> navigateTo("login"));

        // Ustawienia layoutu
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // Dodanie przycisków do layoutu
        add(mainLink,userButton, adminButton);
    }

    private void navigateTo(String route) {
        getUI().ifPresent(ui -> ui.navigate(route));
    }
}
