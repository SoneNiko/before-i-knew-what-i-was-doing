package eu.brickpics.maurice.view.login;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@Tag("sa-login-view")
@Route(value = LoginView.ROUTE)
@PageTitle("Login")
@CssImport("./styles/views/login-view.css")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
    public static final String ROUTE = "login";

    public LoginOverlay login = new LoginOverlay();

    public LoginView() {
        Page page = UI.getCurrent().getPage();

//        getElement().getStyle().set("background-image", "url(frontend/login-view-background.jpg)");
        login.getElement().getStyle().set("background-image", "url(frontend/login-view-background.jpg)");
        login.setAction("login");
        login.setTitle("Maurice");
        login.setDescription("Please login using your password and minecraft username.");
        login.setForgotPasswordButtonVisible(true);
        login.setVisible(true);
        login.setEnabled(true);
        login.setOpened(true);
        login.addForgotPasswordListener(event -> {
            getUI().get().getPage().setLocation("https://youtu.be/dQw4w9WgXcQ");
        });

        add(this.login);

        page.executeJs("document.getElementById('vaadinLoginOverlayWrapper').removeAttribute('with-backdrop')");

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if(!event.getLocation().getQueryParameters().getParameters().getOrDefault("error", Collections.emptyList()).isEmpty()) {
            login.setError(true);
        }
        if(!event.getLocation().getQueryParameters().getParameters().getOrDefault("logout", Collections.emptyList()).isEmpty()) {
            Notification.show("Successfully logged out!", 10, Notification.Position.TOP_CENTER);
        }
    }
}
