package mik.kwi.egz1.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Anchor;

import java.awt.*;

@Route("login")
@Component
public class LoginView extends VerticalLayout {
    public LoginView(){
        setAlignItems(Alignment.CENTER);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        initLayout();
    }
    private void initLayout(){

        // Napis "Pizzeria" z hiperłączem do /main
        Span mainLink = new Span("Pizzeria");
        mainLink.addSingleClickListener(event->navigateTo(""));
        mainLink.getStyle().set("font-size", "24px");
        mainLink.getStyle().set("font-weight", "bold");
        mainLink.getStyle().set("color", "black");
        mainLink.getStyle().set("text-decoration", "none");



        TextField loginField = new TextField("Login:");
        PasswordField passwordField = new PasswordField("Password:");
        Button loginButton = new Button("Log in",new Icon(VaadinIcon.ARROW_RIGHT) , event->{
                String login = loginField.getValue();
                String password = passwordField.getValue();
                if(login.equals("admin") && password.equals("admin123")){
                    navigateTo("AdminPanel");
                }
            else {
                    Notification.show("Wrong login or password!", 3000, Notification.Position.MIDDLE);
                }

        });
        loginButton.setAutofocus(true);
        add(mainLink,loginField, passwordField, loginButton);

    }
    private void navigateTo(String route) {
        getUI().ifPresent(ui -> ui.navigate(route));
    }
}
