package eu.brickpics.maurice.view;

import com.flowingcode.vaadin.addons.xterm.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import eu.brickpics.maurice.view.main.MainView;

import net.kronos.rkon.core.Rcon;
import net.kronos.rkon.core.ex.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Route(value = "", layout = MainView.class)
@PageTitle("Dashboard - Home")
@CssImport("./styles/shared.css")
@CssImport("./styles/views/dashboard-view.css")
//@CssImport(value = "./styles/views/about/about-view.css", include = "lumo-badge")
//@JsModule("@vaadin/vaadin-lumo-styles/badge.js")
public class DashboardView extends Div {

    Logger logger = LoggerFactory.getLogger(DashboardView.class);
    Rcon rcon = null;

    public DashboardView() {

        setId("about-view");
        setSizeFull();

        Board board = new Board();

        Row row1 = board.addRow(createPlayerInfoPanel(), createSupportActivityPanel());
        Row row2 = board.addRow(createPerformancePanel(), createActionPanel());

        add(board);

    }

    public Div createPlayerCard(UUID uuid) {
        Div result = new Div();
        HorizontalLayout layout = new HorizontalLayout();

        Image head = new Image();
        head.setSrc("https://crafatar.com/avatars/" + uuid.toString() + "?size=32&default=MHF_Steve&overlay");
        layout.add(head);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        VerticalLayout userInfoLayout = new VerticalLayout();
//        userInfoLayout.setAlignItems(FlexComponent.Alignment.CENTER);


        H6 h6 = new H6("Name + Clan");
        h6.addClassName("user-name");
        userInfoLayout.add(h6); //TODO: Request data

        Paragraph uuidDisplay = new Paragraph(uuid.toString());
        uuidDisplay.add("");
        userInfoLayout.add(uuidDisplay);


        layout.add(userInfoLayout);

//        layout.setAlignSelf(FlexComponent.Alignment.CENTER);

        result.add(layout);
        return result;
    }

    public Div currentActivityPage() {
        Div result = new Div();

        VerticalLayout currentPlayerListLayout = new VerticalLayout();
//        currentPlayerListLayout.setHeight("100%");
        currentPlayerListLayout.getStyle().set("overflow-y", "scroll");

        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));
        currentPlayerListLayout.add(createPlayerCard(UUID.fromString("bdcef620-3d00-4691-b82a-92f4918c8da7")));

        result.add(currentPlayerListLayout);
        result.setHeight("449px");
        return result;
    }

    public Div createPlayerInfoPanel() {
        Div result = new Div();

        Div innerResult = new Div();
        innerResult.addClassNames("shadow", "m", "card");

        Tab currentActivity = new Tab("Current Activity");
        Div currentActivityPage = currentActivityPage();



        Tab pastActivity = new Tab("Past Activity");
        Div pastActivityPage = new Div();
        pastActivityPage.setText("Past Player Activity");
        pastActivityPage.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(currentActivity, currentActivityPage);
        tabsToPages.put(pastActivity, pastActivityPage);

        Tabs tabs = new Tabs(currentActivity, pastActivity);
//        tabs.setFlexGrowForEnclosedTabs(1);
        tabs.setSelectedTab(currentActivity);

        Div pages = new Div(currentActivityPage, pastActivityPage);
        tabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });
        innerResult.add(tabs, pages);

        result.add(innerResult);
        result.setHeight("449px");
        return result;
    }

    public Div createSupportActivityPanel() {
        Div result = new Div();

        Div innerResult = new Div();
        innerResult.addClassNames("shadow", "m", "card");

        innerResult.setText("Support Activity");

        result.add(innerResult);
        result.setHeight("449px");
        return result;
    }

    public Div createPerformancePanel() {
        Div result = new Div();

        Div innerResult = new Div();
        innerResult.addClassNames("shadow", "m", "card");

        innerResult.setText("Performance");

        result.add(innerResult);
        result.setHeight("449px");
        return result;
    }

    public Div createActionPanel() {
        Div result = new Div();

        Div innerResult = new Div();
        innerResult.addClassNames("shadow", "m", "card");

        MenuBar menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);

        MenuItem restartItem = menuBar.addItem("Restart");

        SubMenu restartSubMenu = restartItem.getSubMenu();
        MenuItem restartSubMenuCountdown = restartSubMenu.addItem("Start the Countdown");
        MenuItem restartSubMenuNow = restartSubMenu.addItem("Restart the server Now");

        MenuItem reloadItem = menuBar.addItem("Reload");


        XTerm xTerm = new XTerm();

        xTerm.writeln("/-----------------------------------------------------------------\\");
        xTerm.writeln("| Es ist momentan nur moeglich Nachrichten zu Senden und nur Ant- |");
        xTerm.writeln("| worten auf genau diese Nachrichten zu empfangen.                |");
        xTerm.writeln("| Man kann also eine Nachricht an die Console senden und man      |");
        xTerm.writeln("| kriegt eine Antwort, aber man kann keinen Anderen Traffic sehen |");
        xTerm.writeln("| Ich arbeite daran das zu fixen.                                 |");
        xTerm.writeln("\\-----------------------------------------------------------------/");

        xTerm.setCursorBlink(true);
        xTerm.setCursorStyle(ITerminalOptions.CursorStyle.BAR);
        xTerm.setSizeFull();
        xTerm.loadFeature(new XTermClipboard(), xTermClipboard -> {
            xTermClipboard.setCopySelection(true);
            xTermClipboard.setUseSystemClipboard(XTermClipboard.UseSystemClipboard.READWRITE);
            xTermClipboard.setPasteWithRightClick(true);
        });



        try {
            rcon = new Rcon("127.0.0.1", 25575, "Sonnenschein123".getBytes());
        } catch (AuthenticationException | IOException exception) {
            xTerm.writeln("Unable to connect to Rcon");
            logger.error("Unable to connect to Rcon");
        }

        if (rcon != null) {
            xTerm.loadFeature(new XTermConsole(), console -> {
                console.addLineListener(event -> {
                    String line = event.getLine();
                    logger.info("Command Executed by: " + SecurityContextHolder.getContext().getAuthentication().getName() + " in Dashboard Console: " + line);
                    try {
                        xTerm.writeln(rcon.command(line));
                    } catch (IOException e) {
                        xTerm.writeln("Error: " + e.getMessage());
                        logger.error("Error: " + e.getMessage() + ", User: " + SecurityContextHolder.getContext().getAuthentication().getName());
                    }
                });
            });
        }
        xTerm.focus();
        xTerm.getFeature(XTermFit.class).ifPresent(XTermFit::fit);

        innerResult.add(xTerm);


        restartSubMenuCountdown.addClickListener(event -> {
            xTerm.writeln("Restart Countdown started");
            if (rcon != null) {
                try {
                    xTerm.writeln(rcon.command("rs start"));
                } catch (IOException e) {
                    xTerm.writeln("Exception occured: Check Maurice logs for more details");
                    logger.error("Rcon exception: " + e.getMessage());
                }
            }
        });

        restartSubMenuNow.addClickListener(event -> {
            xTerm.writeln("Server stopped");
            if (rcon != null) {
                try {
                    xTerm.writeln(rcon.command("stop"));
                } catch (IOException e) {
                    xTerm.writeln("Exception occured: Check Maurice logs for more details");
                    logger.error("Rcon exception: " + e.getMessage());
                }
            }
        });

        reloadItem.addClickListener(event -> {
            xTerm.writeln("Reload Command sent");
            if (rcon != null) {
                try {
                    xTerm.writeln(rcon.command("rl confirm"));
                } catch (IOException e) {
                    xTerm.writeln("Exception occured: Check Maurice logs for more details");
                    logger.error("Rcon exception: " + e.getMessage());
                }
            }
        });

        result.add(innerResult);
        result.setHeight("449px");
        return result;
    }



}
