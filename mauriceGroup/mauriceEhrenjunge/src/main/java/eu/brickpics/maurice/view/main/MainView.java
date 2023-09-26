package eu.brickpics.maurice.view.main;

import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import eu.brickpics.maurice.view.DashboardView;
import org.springframework.security.core.context.SecurityContextHolder;

@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/main-view.css")
@CssImport("./styles/shared.css")
@PWA(
        name = "Maurice the Bricklayer",
        shortName = "Maurice",
        enableInstallPrompt = true,
        iconPath = "frontend/icon.png",
        description = "Maurice... what more do you want from me. I didn't write a fucking book to go along with this." +
                " What? you gonna cry now?. You Crybaby ass wimp. How about you piss your pants you little crybaby."
)
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends AppLayout {

    private final Tabs menu;
    private H1 viewTitle;
    private ConfirmDialog dialog;

    public MainView() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
//        layout.getClassNames().add("");

        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());

        viewTitle = new H1();

        layout.add(viewTitle);

        Image profilePic = new Image();

        try {
            profilePic = new Image("http://minotar.net/helm/" + SecurityContextHolder.getContext().getAuthentication().getName() + "/100.png", "Avatar");
        } catch (NullPointerException ignored) { }

        dialog = new ConfirmDialog(
                "Logout", "Are you sure you want to logout. You'll be redirected to the login page once you've logged out",
                "Logout", this::onLogout,
                "Blyat", this::onCancel
        );
        dialog.setConfirmButtonTheme("error primary");


        profilePic.addClickListener(event -> dialog.open());
        profilePic.addClassName("pointer");

        layout.add(profilePic, dialog);

        return layout;
    }

    private void onCancel(ConfirmDialog.CancelEvent cancelEvent) {
        dialog.close();
    }
    private void onLogout(ConfirmDialog.ConfirmEvent confirmEvent) {
        UI.getCurrent().getPage().setLocation("/logout");
    }




    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setId("drawer");
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("frontend/icon.png", "Maurice Logo"));

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        verticalLayout.setWidth("fit-content");
        verticalLayout.setSpacing(false);
        verticalLayout.setPadding(false);

        H1 h1 = new H1("Maurice");
        h1.setWidthFull();

        verticalLayout.add(h1);

        verticalLayout.add(new Paragraph("brickpics.eu"));

        logoLayout.add(verticalLayout);
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {
        return new Tab[] {
                createTab("Dashboard", DashboardView.class)
        };
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        return getContent().getClass().getAnnotation(PageTitle.class).value();
    }
}
